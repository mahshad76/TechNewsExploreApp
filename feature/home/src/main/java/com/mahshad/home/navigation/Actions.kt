package com.mahshad.home.navigation

import DetailScreen
import androidx.navigation.NavController

fun NavController.navigateFromHomeToDetail(article: Article) =
    this.navigate(DetailScreen(subject = subject))