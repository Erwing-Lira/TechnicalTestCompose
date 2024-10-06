package com.example.technicaltest.home.state

sealed interface ProcessState {
    data object Loading: ProcessState
    data object Success: ProcessState
    data object Failure: ProcessState
}