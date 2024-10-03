package com.example.technicaltest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.technicaltest.navigation.routes.Routes

@Composable
fun Navigation(
    navigationController: NavHostController
) {
    NavHost(
        navController = navigationController,
        startDestination = Routes.SignIn.route
    ) {
        composable(route = Routes.SignIn.route) {  }
        composable(route = Routes.SignUp.route) {  }
        composable(route = Routes.Home.route) {  }
        composable(route = Routes.MovementDetail.route) {  }
    }
}