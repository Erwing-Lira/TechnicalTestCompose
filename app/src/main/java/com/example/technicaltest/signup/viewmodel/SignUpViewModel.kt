package com.example.technicaltest.signup.viewmodel

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.example.technicaltest.signin.usecase.ValidateEmailUseCase
import com.example.technicaltest.signup.usecase.ValidateTextUseCase
import com.example.technicaltest.signin.usecase.ValidatePassUseCase
import com.example.technicaltest.signup.state.SignUpState
import com.example.technicaltest.views.buttons.ButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateTextUseCase: ValidateTextUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePassUseCase: ValidatePassUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    val uiState: StateFlow<SignUpState> = _uiState.asStateFlow()

    fun onNameChanged(name: String) {
        _uiState.update {
            it.copy(
                name = name
            )
        }
        validateInputs()
    }

    fun onLastNameChanged(lastName: String) {
        _uiState.update {
            it.copy(
                lastName = lastName
            )
        }
        validateInputs()
    }

    fun onEmailChanged(email: String) {
        _uiState.update {
            it.copy(
                email = email
            )
        }
        validateInputs()
    }

    fun onPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                password = password
            )
        }
        validatePasswords()
        validateInputs()
    }

    fun onRepeatPasswordChanged(password: String) {
        _uiState.update {
            it.copy(
                repeatPassword = password
            )
        }
        validatePasswords()
        validateInputs()
    }

    fun onPasswordVisibility() {
        val visibility = _uiState.value.passwordVisibility
        _uiState.update {
            it.copy(
                passwordVisibility = !visibility
            )
        }
    }

    fun onRepeatPasswordVisibility() {
        val visibility = _uiState.value.repeatPasswordVisibility
        _uiState.update {
            it.copy(
                repeatPasswordVisibility = !visibility
            )
        }
    }

    private fun validatePasswords() {
        val bothPasswordMatches = _uiState.value.password == _uiState.value.repeatPassword
        _uiState.update {
            it.copy(
                bothPasswordMatches = bothPasswordMatches
            )
        }
    }

    private fun validateInputs() {
        val isValidName = validateTextUseCase.isValidText(_uiState.value.name)
        val isValidLastName = validateTextUseCase.isValidText(_uiState.value.lastName)
        val isValidEmail = validateEmailUseCase.isValidEmail(Patterns.EMAIL_ADDRESS, _uiState.value.email)
        val isValidPass = validatePassUseCase.isValidPassword(_uiState.value.password)
        val isValidRepeatPass = validatePassUseCase.isValidPassword(_uiState.value.repeatPassword)

        val buttonEnabled = isValidEmail && _uiState.value.bothPasswordMatches
        _uiState.update {
            it.copy(
                isNameWrong = !isValidName,
                isLastNameWrong = !isValidLastName,
                isEmailWrong = !isValidEmail,
                isPassWrong = !isValidPass,
                isRepeatPassWrong = !isValidRepeatPass,
                isButtonEnabled = buttonEnabled
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
}