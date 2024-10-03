package com.example.technicaltest.navigation.routes

sealed class Routes(val route: String) {
    data object SignIn: Routes("sign-in")
    data object SignUp: Routes("sign-up")
    data object Home: Routes("home")
    data object MovementDetail: Routes("movement-detail/{id}") {
        fun createRoute(id: Int) = "movement-detail/$id"
    }
}