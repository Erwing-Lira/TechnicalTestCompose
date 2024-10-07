package com.example.technicaltest.signup.viewmodel

import android.net.Uri
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltest.signin.state.ProcessState
import com.example.technicaltest.signin.usecase.ValidateEmailUseCase
import com.example.technicaltest.signup.usecase.ValidateTextUseCase
import com.example.technicaltest.signin.usecase.ValidatePassUseCase
import com.example.technicaltest.signup.repository.ISignUpRegistry
import com.example.technicaltest.signup.state.SignUpState
import com.example.technicaltest.views.buttons.ButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateTextUseCase: ValidateTextUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePassUseCase: ValidatePassUseCase,
    private val signUpRegistry: ISignUpRegistry
): ViewModel() {
    private val _uiState = MutableStateFlow(SignUpState())
    val uiState: StateFlow<SignUpState> = _uiState.asStateFlow()

    private val _processState = MutableStateFlow<ProcessState>(ProcessState.StandBy)
    val processState: StateFlow<ProcessState> = _processState.asStateFlow()

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
        validateInputs()
    }

    fun onPhotoChanged(uri: Uri) {
        _uiState.update {
            it.copy(
                photoUri = uri
            )
        }
        validateInputs()
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
                bothPasswordMatches = bothPasswordMatches,
            )
        }
    }

    fun resetProcessState() {
        _processState.value = ProcessState.StandBy
    }

    private fun validateInputs() {
        val isValidName = validateTextUseCase.invoke(_uiState.value.name)
        val isValidLastName = validateTextUseCase.invoke(_uiState.value.lastName)
        val isValidEmail = validateEmailUseCase.invoke(Patterns.EMAIL_ADDRESS, _uiState.value.email)
        val isValidPass = validatePassUseCase.invoke(_uiState.value.password)
        val isValidRepeatPass = validatePassUseCase.invoke(_uiState.value.repeatPassword)
        val isValidPhoto = _uiState.value.photoUri != Uri.EMPTY

        val buttonEnabled = isValidEmail && _uiState.value.bothPasswordMatches && isValidName && isValidLastName && isValidPhoto
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

    fun registry() {
        if (_uiState.value.buttonState is ButtonState.Loading) {
            return
        }
        _uiState.update {
            it.copy(
                buttonState = ButtonState.Loading
            )
        }
        viewModelScope.launch {
            signUpRegistry.registry(
                name = _uiState.value.name,
                lastName = _uiState.value.lastName,
                email = _uiState.value.email,
                password = _uiState.value.password,
                photoUri = _uiState.value.photoUri
            ).fold(
                onSuccess = {
                    _uiState.update {
                        it.copy(
                            buttonState = ButtonState.StandBy,
                        )
                    }
                    _processState.value = ProcessState.Success
                },
                onFailure = {
                    _uiState.update {
                        it.copy(
                            buttonState = ButtonState.StandBy,
                        )
                    }
                    _processState.value = ProcessState.Failure
                }
            )
        }
    }
}