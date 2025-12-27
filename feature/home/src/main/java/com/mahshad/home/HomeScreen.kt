package com.mahshad.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.mahshad.ui.components.SwipeableTabScreen
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Composable
fun HomeScreen() {
    SwipeableTabScreen(
        listOf(
            "News" to painterResource(com.mahshad.ui.R.drawable.news_paper_svgrepo_com),
            "Favorite" to painterResource(com.mahshad.ui.R.drawable.favorite_svgrepo_com)
        ), { index: Int ->
            when (index) {
                0 -> NewsScreen()
                else -> FavoriteNewsScreen()
            }
        })
}