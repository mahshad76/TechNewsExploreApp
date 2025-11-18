package com.mahshad.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import kotlinx.serialization.Serializable

@Serializable
data object ProfileNavigationGraphRoute

fun NavGraphBuilder.profileNavigationGraph(navController: NavController) {}