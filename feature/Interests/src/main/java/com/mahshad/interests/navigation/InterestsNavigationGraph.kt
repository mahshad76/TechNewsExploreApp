package com.mahshad.interests.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mahshad.interests.InterestsScreen
import com.mahshad.interests.InterestsScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object InterestsNavigationGraphRoute

fun NavGraphBuilder.interestsNavigationGraph(navController: NavController) {
    navigation<InterestsNavigationGraphRoute>(startDestination = InterestsScreenRoute) {
        composable<InterestsScreenRoute> {
            InterestsScreen()
        }
    }
}