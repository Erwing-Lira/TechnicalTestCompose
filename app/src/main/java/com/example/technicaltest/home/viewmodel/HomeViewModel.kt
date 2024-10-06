package com.example.technicaltest.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltest.home.repository.ILogOutRepository
import com.example.technicaltest.home.repository.IRegistryCardRepository
import com.example.technicaltest.home.repository.IUserRepository
import com.example.technicaltest.home.state.CardState
import com.example.technicaltest.home.state.HomeState
import com.example.technicaltest.home.state.MovementsState
import com.example.technicaltest.home.usecase.FetchMovementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val logOutRepository: ILogOutRepository,
    private val userRepository: IUserRepository,
    private val registryCardRepository: IRegistryCardRepository,
    private val fetchMovementsUseCase: FetchMovementsUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    private val _cardState = MutableStateFlow(CardState())
    val cardState: StateFlow<CardState> = _cardState.asStateFlow()

    private val _movementsState = MutableStateFlow(MovementsState())
    val movementsState: StateFlow<MovementsState> = _movementsState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            getData()
            getMovements()
        }
    }

    private suspend fun getData() {
        delay(2_000)
        userRepository.getUserInfo().fold(
            onSuccess = { user ->
                _cardState.update { state ->
                    state.copy(
                        name = user.name,
                        cardNumber = user.cardNumber,
                        expiresDate = user.expiresDate,
                        cvv = user.cvv,
                        isLoading = false
                    )
                }
            },
            onFailure = {
            }
        )
    }

    private suspend fun getMovements() {
        val result = fetchMovementsUseCase.invoke()
        delay(5_000)
        _movementsState.update {
            it.copy(
                isLoading = result.isEmpty(),
                list = result
            )
        }
    }

    fun onRegistryCard() {
        viewModelScope.launch {
            registryCardRepository.registryCard().fold(
                onSuccess = {
                    _cardState.update { state ->
                        state.copy(
                            isLoading = true
                        )
                    }
                    _movementsState.update {
                        it.copy(
                            isLoading = true,
                        )
                    }
                },
                onFailure = {}
            )
        }
    }

    fun onLogOut() {
        logOutRepository.logOut()
        _uiState.update {
            it.copy(
                isLogOut = true,
                showLogOut = false
            )
        }
    }

    fun showDialog() {
        _uiState.update {
            it.copy(
                showLogOut = true
            )
        }
    }

    fun hideDialog() {
        _uiState.update {
            it.copy(
                showLogOut = false
            )
        }
    }
}