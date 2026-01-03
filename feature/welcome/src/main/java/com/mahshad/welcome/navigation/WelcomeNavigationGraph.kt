package com.mahshad.welcome.navigation

import LoginScreen
import LoginScreenRoute
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mahshad.welcome.signup.SignUpScreen
import com.mahshad.welcome.signup.SignUpScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object WelcomeGraphRoute

fun NavGraphBuilder.welcomeNavigationGraph(
    navController: NavController,
    navigateToHome: (String) -> Unit
) {
    navigation<WelcomeGraphRoute>(LoginScreenRoute::class) {
        composable<SignUpScreenRoute> {
            SignUpScreen(onNavigateToLogin = {
                navController.popBackStack()
            })
        }
        composable<LoginScreenRoute> {
            LoginScreen(
                onNavigateToHome = { navigateToHome(it) },
                onNavigateToSignUp = {
                    navController.navigate(SignUpScreenRoute)
                })
        }
    }
}