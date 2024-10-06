package com.example.technicaltest.home.state

data class HomeState(
    val showLogOut: Boolean = false,
    val isLogOut: Boolean = false
)

data class CardState(
    val isLoading: Boolean = true,
    val name: String = "",
    val cardNumber: String = "",
    val expiresDate: String = "",
    val cvv: String = ""
)

data class MovementsState(
    val isLoading: Boolean = true,
    val list: Map<String, List<Movement>> = mapOf()
)