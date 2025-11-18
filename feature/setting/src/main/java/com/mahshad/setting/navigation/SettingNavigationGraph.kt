package com.mahshad.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import kotlinx.serialization.Serializable

@Serializable
data object SettingNavigationGraphRoute

fun NavGraphBuilder.settingNavigationGraph(navController: NavController) {
}