package com.example.technicaltest.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltest.home.model.CardInformation
import com.example.technicaltest.home.repository.ICardsRepository
import com.example.technicaltest.home.repository.ILogOutRepository
import com.example.technicaltest.home.repository.IUserRepository
import com.example.technicaltest.home.state.CardState
import com.example.technicaltest.home.state.HomeState
import com.example.technicaltest.home.state.InformationState
import com.example.technicaltest.home.state.MovementsState
import com.example.technicaltest.home.state.ProcessState
import com.example.technicaltest.home.usecase.FetchMovementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val cardsRepository: ICardsRepository,
    private val fetchMovementsUseCase: FetchMovementsUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    private val _userState = MutableStateFlow(InformationState())
    val userState: StateFlow<InformationState> = _userState.asStateFlow()

    private val _cardState = MutableStateFlow(CardState())
    val cardState: StateFlow<CardState> = _cardState.asStateFlow()

    private val _movementsState = MutableStateFlow(MovementsState())
    val movementsState: StateFlow<MovementsState> = _movementsState.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            getUserInfo()
            getCardInfo()
            getMovementsInfo()
        }
    }

    private suspend fun getUserInfo() {
        userRepository.getUserInfo().fold(
            onSuccess = { name ->
                _userState.update {
                    it.copy(
                        processState = ProcessState.Success,
                        name = name
                    )
                }
            },
            onFailure = {
                _userState.update {
                    it.copy(
                        processState = ProcessState.Failure,
                    )
                }
            }
        )
    }

    private suspend fun getCardInfo() {
        cardsRepository.getFirstCard().fold(
            onSuccess = { card ->
                _cardState.update {
                    it.copy(
                        processState = ProcessState.Success,
                        cardInformation = CardInformation(
                            id = card.id,
                            cardNumber = card.number,
                            expiresDate = card.expiresDate,
                            cvv = card.cvv
                        )
                    )
                }
            },
            onFailure = {
                _cardState.update {
                    it.copy(
                        processState = ProcessState.Failure
                    )
                }
            }
        )
    }

    private suspend fun getMovementsInfo() {
        val result = fetchMovementsUseCase.invoke(
            _cardState.value.cardInformation.id
        )
        result?.let { list ->
            _movementsState.update {
                it.copy(
                    processState = if (list.isEmpty()) {
                        ProcessState.Loading
                    } else {
                        ProcessState.Success
                    },
                    movementList = list
                )
            }
        } ?: run {
            _movementsState.update {
                it.copy(
                    processState = ProcessState.Failure
                )
            }
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