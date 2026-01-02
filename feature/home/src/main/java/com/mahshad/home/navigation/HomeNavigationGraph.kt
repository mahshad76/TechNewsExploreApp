package com.mahshad.home.navigation

import DetailScreen
import DetailScreenRoute
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.mahshad.home.HomeScreen
import com.mahshad.home.HomeScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraphRoute


fun NavGraphBuilder.homeNavigationGraph(navController: NavController) {
    navigation<HomeGraphRoute>(startDestination = HomeScreenRoute::class) {
        composable<HomeScreenRoute> {
            val homeGraphEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry<HomeGraphRoute>()
            }
            HomeScreen()
        }
        composable<DetailScreenRoute> {
            DetailScreen()
        }
    }
}