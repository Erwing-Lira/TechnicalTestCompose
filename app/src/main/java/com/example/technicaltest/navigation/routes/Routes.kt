package com.example.technicaltest.navigation.routes

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object SignIn: Routes()

    @Serializable
    data object SignUp: Routes()

    @Serializable
    data object Home: Routes()

    @Serializable
    data class MovementDetail(
        val reference: String
    ): Routes()
}