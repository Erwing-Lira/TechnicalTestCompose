package com.example.technicaltest.signup.state

import android.net.Uri
import com.example.technicaltest.views.buttons.ButtonState

data class SignUpState(
    val name: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val photoUri: Uri = Uri.EMPTY,
    val passwordVisibility: Boolean = false,
    val repeatPasswordVisibility: Boolean = false,

    val isNameWrong: Boolean = false,
    val isLastNameWrong: Boolean = false,
    val isEmailWrong: Boolean = false,
    val isPassWrong: Boolean = false,
    val isRepeatPassWrong: Boolean = false,
    val bothPasswordMatches: Boolean = true,

    val isButtonEnabled: Boolean = false,
    val buttonState: ButtonState = ButtonState.StandBy,
)