package com.mahshad.home.navigation

import DetailScreen
import DetailScreenRoute
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.mahshad.home.HomeScreen
import com.mahshad.home.HomeScreenRoute
import com.mahshad.model.Article
import kotlinx.serialization.Serializable

@Serializable
data object HomeGraphRoute


fun NavGraphBuilder.homeNavigationGraph(navController: NavController) {
    navigation<HomeGraphRoute>(startDestination = HomeScreenRoute::class) {
        composable<HomeScreenRoute> {
            HomeScreen({ article: Article ->
                navController.navigateFromHomeToDetail(article)
            })
        }
        composable<DetailScreenRoute> { backStackEntry ->
            val detailScreenRoute = backStackEntry.toRoute<DetailScreenRoute>()
            DetailScreen(detailScreenRoute.article)
        }
    }
}