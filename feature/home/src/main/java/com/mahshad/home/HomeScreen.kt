package com.mahshad.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.mahshad.ui.components.SwipeableTabScreen
import com.mahshad.ui.icons.TneIcons.Favorite
import com.mahshad.ui.icons.TneIcons.News
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Composable
fun HomeScreen() {
    SwipeableTabScreen(
        listOf(
            "News" to painterResource(News),
            "Favorite" to painterResource(Favorite)
        ), { index: Int ->
            when (index) {
                0 -> NewsScreen()
                else -> FavoriteNewsScreen()
            }
        })
}