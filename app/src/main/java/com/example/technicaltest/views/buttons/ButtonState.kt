package com.example.technicaltest.views.buttons

sealed class ButtonState {
    data object StandBy: ButtonState()
    data object Loading: ButtonState()
}