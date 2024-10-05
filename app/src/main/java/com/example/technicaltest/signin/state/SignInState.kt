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