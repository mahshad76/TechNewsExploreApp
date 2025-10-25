package com.mahshad.recipeexploreapp.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.mahshad.recipeexploreapp.navigation.RecipeExploreDestinations.HOME_ROUTE
import com.mahshad.recipeexploreapp.navigation.RecipeExploreDestinations.PROFILE_ROUTE
import com.mahshad.recipeexploreapp.navigation.RecipeExploreDestinations.SETTING_ROUTE

object RecipeExploreDestinations {
    const val HOME_ROUTE = "home"
    const val PROFILE_ROUTE = "profile"
    const val SETTING_ROUTE = "setting"
}

class RecipeExploreNavigationActions(private val navController: NavHostController) {
    fun navigateToHome() = navController.navigate(HOME_ROUTE) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    fun navigateToProfile() = navController.navigate(PROFILE_ROUTE) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    fun navigateToSetting() = navController.navigate(SETTING_ROUTE) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}