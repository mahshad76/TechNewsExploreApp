package com.mahshad.home.navigation

import DetailScreenRoute
import androidx.navigation.NavController
import com.mahshad.model.Article

fun NavController.navigateFromHomeToDetail(article: Article) =
    this.navigate(DetailScreenRoute(article = article))