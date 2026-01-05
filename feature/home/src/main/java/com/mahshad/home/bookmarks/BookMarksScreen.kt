package com.mahshad.home.bookmarks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import com.mahshad.home.news.NewsScreenViewModel

@Composable
fun BookMarksScreen(parentEntryProvider: () -> NavBackStackEntry) {
    val parentEntry = remember { parentEntryProvider() }
    val viewModel: NewsScreenViewModel = hiltViewModel(parentEntry)

}