package com.example.technicaltest.signin.state

import com.example.technicaltest.views.buttons.ButtonState

data class SignInState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,

    val isEmailWrong: Boolean = false,
    val isPassWrong: Boolean = false,

    val isButtonEnabled: Boolean = false,
    val buttonState: ButtonState = ButtonState.StandBy,
)

sealed interface ProcessState {
    data object Success: ProcessState
    data object Failure: ProcessState
    data object StandBy: ProcessState
}