package com.mahshad.recipeexploreapp.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.mahshad.home.navigation.HomeGraphRoute
import com.mahshad.interests.navigation.InterestsNavigationGraphRoute
import com.mahshad.setting.navigation.SettingNavigationGraphRoute

class TechExploreNavigationActions(private val navController: NavHostController) {
    fun navigateToHome() = navController.navigate(HomeGraphRoute) {
//        popUpTo(navController.graph.findStartDestination().id) {
//            saveState = true
//        }
//        launchSingleTop = true
//        restoreState = true
    }

    fun navigateToInterests() = navController.navigate(InterestsNavigationGraphRoute) {
//        popUpTo(navController.graph.findStartDestination().id) {
//            saveState = true
//        }
//        launchSingleTop = true
//        restoreState = true
    }

    fun navigateToSetting() = navController.navigate(SettingNavigationGraphRoute) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}