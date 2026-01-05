package com.mahshad.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import com.mahshad.home.bookmarks.BookMarksScreen
import com.mahshad.home.favorites.FavoriteNewsScreen
import com.mahshad.home.news.NewsScreen
import com.mahshad.model.Article
import com.mahshad.ui.components.SwipeableTabScreen
import com.mahshad.ui.icons.TneIcons.Favorite
import com.mahshad.ui.icons.TneIcons.FilledBookMark
import com.mahshad.ui.icons.TneIcons.FilledFavorite
import com.mahshad.ui.icons.TneIcons.FilledNews
import com.mahshad.ui.icons.TneIcons.News
import com.mahshad.ui.icons.TneIcons.UnFilledBookMark
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Composable
fun HomeScreen(
    navigateToDetail: (Article) -> Unit,
    parentEntryProvider: () -> NavBackStackEntry
) {
    SwipeableTabScreen(
        listOf(
            Triple(
                "News", painterResource(News),
                painterResource(FilledNews)
            ),
            Triple(
                "Favorite", painterResource(Favorite),
                painterResource(FilledFavorite)
            ),
            Triple(
                "Bookmarks", painterResource(UnFilledBookMark),
                painterResource(FilledBookMark)
            )
        ), { index: Int ->
            when (index) {
                0 -> NewsScreen(navigateToDetail, parentEntryProvider)
                1 -> FavoriteNewsScreen(navigateToDetail)
                else -> BookMarksScreen(parentEntryProvider)
            }
        })
}