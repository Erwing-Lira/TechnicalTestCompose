package com.example.technicaltest.signin.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.technicaltest.signin.state.SignInState
import com.example.technicaltest.signin.usecase.ValidateEmailUseCase
import com.example.technicaltest.signin.usecase.ValidatePassUseCase
import com.example.technicaltest.views.buttons.ButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validatePassUseCase: ValidatePassUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(SignInState())
    val uiState: StateFlow<SignInState> = _uiState.asStateFlow()

    fun onEmailChanged(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }
        validateInputs()
    }

    fun onPasswordChange(value: String) {
        _uiState.update {
            it.copy(
                password = value
            )
        }
        validateInputs()
    }

    fun onPasswordVisibilityChange() {
        val visibility = _uiState.value.passwordVisibility
        _uiState.update {
            it.copy(
                passwordVisibility = !visibility
            )
        }
    }

    fun authenticate() {
        if (_uiState.value.buttonState is ButtonState.Loading) {
            return
        }
        _uiState.update {
            it.copy(
                buttonState = ButtonState.Loading
            )
        }
    }

    private fun validateInputs() {
        val isValidEmail = validateEmailUseCase.isValidEmail(Patterns.EMAIL_ADDRESS, _uiState.value.email)
        val isValidPass = validatePassUseCase.isValidPassword(_uiState.value.password)
        val buttonEnabled = isValidEmail && isValidPass && _uiState.value.email.isNotEmpty() && _uiState.value.password.isNotEmpty()
        _uiState.update {
            it.copy(
                isEmailWrong = !isValidEmail,
                isPassWrong = !isValidPass,
                isButtonEnabled = buttonEnabled
            )
        }
    }
}