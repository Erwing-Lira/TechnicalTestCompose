package com.example.technicaltest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.technicaltest.home.view.HomeView
import com.example.technicaltest.navigation.routes.Routes
import com.example.technicaltest.signin.view.SignInView
import com.example.technicaltest.signup.view.SignUpView

@Composable
fun Navigation(
    navigationController: NavHostController
) {
    NavHost(
        navController = navigationController,
        startDestination = Routes.SignIn
    ) {
        composable<Routes.SignIn> {
            SignInView(
                onNavigateToSignUp = {
                    navigationController.navigate(Routes.SignUp)
                },
                onNavigateToHome = {
                    navigationController.navigate(Routes.Home) {
                        popUpTo<Routes.SignIn> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.SignUp> {
            SignUpView(
                onNavigateUp = {
                    navigationController.navigateUp()
                },
                onSignUpSuccess = {
                    navigationController.navigate(Routes.SignIn) {
                        popUpTo<Routes.SignUp> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.Home> {
            HomeView(
                onMovementClicked = { reference ->
                    navigationController.navigate(Routes.MovementDetail(reference))
                },
                onLogoutClicked = {
                    navigationController.navigate(Routes.SignIn) {
                        popUpTo<Routes.Home> {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Routes.MovementDetail> {

        }
    }
}