package com.mahshad.interests.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import kotlinx.serialization.Serializable

@Serializable
data object InterestsNavigationGraphRoute

fun NavGraphBuilder.interestsNavigationGraph(navController: NavController) {}