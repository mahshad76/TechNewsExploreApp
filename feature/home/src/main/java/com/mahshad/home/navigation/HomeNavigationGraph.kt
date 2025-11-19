package com.mahshad.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.mahshad.home.DetailScreen
import com.mahshad.home.DetailScreenRoute
import com.mahshad.home.HomeScreen
import com.mahshad.home.HomeScreenRoute
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraphRoute

fun NavGraphBuilder.homeNavigationGraph(navController: NavController) {
    navigation<HomeGraphRoute>(startDestination = HomeScreenRoute::class) {
        composable<HomeScreenRoute> {
            HomeScreen({ subject: String ->
                navController.navigateFromHomeToDetail(subject = subject)
            })
        }
        composable<DetailScreenRoute> { backStackEntry ->
            val detailRoute = backStackEntry.toRoute<DetailScreenRoute>()
            DetailScreen(detailRoute.subject)
        }
    }
}