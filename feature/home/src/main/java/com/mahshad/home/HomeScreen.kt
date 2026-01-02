package com.mahshad.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.mahshad.model.Article
import com.mahshad.ui.components.SwipeableTabScreen
import com.mahshad.ui.icons.TneIcons.Favorite
import com.mahshad.ui.icons.TneIcons.FilledFavorite
import com.mahshad.ui.icons.TneIcons.FilledNews
import com.mahshad.ui.icons.TneIcons.News
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Composable
fun HomeScreen(navigateToDetail: (Article) -> Unit) {
    SwipeableTabScreen(
        listOf(
            Triple(
                "News", painterResource(News),
                painterResource(FilledNews)
            ),
            Triple(
                "Favorite", painterResource(Favorite),
                painterResource(FilledFavorite)
            )
        ), { index: Int ->
            when (index) {
                0 -> NewsScreen(navigateToDetail)
                else -> FavoriteNewsScreen(navigateToDetail)
            }
        })
}