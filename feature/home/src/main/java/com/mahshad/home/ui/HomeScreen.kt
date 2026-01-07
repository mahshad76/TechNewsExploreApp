package com.mahshad.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavBackStackEntry
import com.mahshad.home.bookmarks.BookMarksScreen
import com.mahshad.home.favorites.FavoriteNewsScreen
import com.mahshad.home.news.NewsScreen
import com.mahshad.model.Article
import com.mahshad.ui.components.SwipeableTabScreen
import com.mahshad.ui.icons.TneIcons.FilledBookMark
import com.mahshad.ui.icons.TneIcons.FilledNews
import com.mahshad.ui.icons.TneIcons.Interests
import com.mahshad.ui.icons.TneIcons.News
import com.mahshad.ui.icons.TneIcons.UnFilledBookMark
import com.mahshad.ui.icons.TneIcons.UnFilledInterests
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
                "Interests", rememberVectorPainter(UnFilledInterests),
                rememberVectorPainter(Interests)
            ),
            Triple(
                "Bookmarks", painterResource(UnFilledBookMark),
                painterResource(FilledBookMark)
            )
        ), { index: Int ->
            when (index) {
                // it seems like the first time that i see the tabs they are correct but for the second visit, there is somethin wrong
                0 -> NewsScreen(navigateToDetail, parentEntryProvider)
                1 -> FavoriteNewsScreen(navigateToDetail)
                else -> BookMarksScreen(navigateToDetail, parentEntryProvider)
            }
        })
}