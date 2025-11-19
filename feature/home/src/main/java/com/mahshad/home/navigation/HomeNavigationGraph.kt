package com.mahshad.home.navigation

import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.mahshad.home.DetailScreen
import com.mahshad.home.DetailScreenRoute
import com.mahshad.home.HomeScreen
import com.mahshad.home.HomeScreenRoute
import com.mahshad.home.HomeScreenViewModel
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraphRoute


fun NavGraphBuilder.homeNavigationGraph(navController: NavController) {
    navigation<HomeGraphRoute>(startDestination = HomeScreenRoute::class) {
        composable<HomeScreenRoute> {
            val homeGraphEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry<HomeGraphRoute>()
            }
            val sharedViewModel: HomeScreenViewModel = hiltViewModel(homeGraphEntry)
            HomeScreen({ subject: String ->
                navController.navigateFromHomeToDetail(subject = subject)
            }, sharedViewModel)
        }
        composable<DetailScreenRoute> { backStackEntry ->
            val homeGraphEntry = remember(navController.currentBackStackEntry) {
                navController.getBackStackEntry<HomeGraphRoute>()
            }
            val sharedViewModel: HomeScreenViewModel = hiltViewModel(homeGraphEntry)
            val detailRoute = backStackEntry.toRoute<DetailScreenRoute>()
            DetailScreen(detailRoute.subject, sharedViewModel)
        }
    }
}