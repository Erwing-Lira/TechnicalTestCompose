package com.example.technicaltest.home.state

data class InformationState(
    val processState: ProcessState = ProcessState.Loading,
    val name: String = "",
)