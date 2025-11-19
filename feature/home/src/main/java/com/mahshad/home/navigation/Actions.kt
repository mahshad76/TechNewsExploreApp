package com.mahshad.home.navigation

import androidx.navigation.NavController
import com.mahshad.home.DetailScreenRoute

fun NavController.navigateFromHomeToDetail(subject: String) =
    this.navigate(DetailScreenRoute(subject = subject))