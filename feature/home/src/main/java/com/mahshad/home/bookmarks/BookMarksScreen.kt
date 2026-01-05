package com.mahshad.home.bookmarks

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.mahshad.home.NewsFeedUiState
import com.mahshad.home.news.NewsScreenViewModel
import com.mahshad.home.ui.HomeSearchBar
import com.mahshad.home.ui.SearchSuggestionsBox
import com.mahshad.model.Article
import com.mahshad.ui.components.TneLoadingWheel

@Composable
fun BookMarksScreen(
    navigateToDetail: (Article) -> Unit,
    parentEntryProvider: () -> NavBackStackEntry
) {
    val parentEntry = remember { parentEntryProvider() }
    val viewModel: NewsScreenViewModel = hiltViewModel(parentEntry)
    val searchQuery = viewModel._searchQueryStateFlow.collectAsStateWithLifecycle()
    val searchSuggestion = viewModel.searchSuggestions.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        HomeSearchBar(
            modifier = Modifier.padding(bottom = 7.dp),
            searchQuery.value,
            { viewModel.updateSearchQueryFlow(it) },
            {
                //navigateFromHomeToDetail(searchQuery.value)
            })
        val searchSuggestionValue = searchSuggestion.value
        when (searchSuggestionValue) {
            is NewsFeedUiState.Successful -> {
                SearchSuggestionsBox(
                    searchSuggestionValue.news.filter { it.isLiked },
                    { article: Article ->
                        navigateToDetail(article)
                    },
                    { viewModel.bookmarkClicked(it) }
                )
            }

            is NewsFeedUiState.Error -> Toast.makeText(
                context, "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()

            is NewsFeedUiState.Loading -> TneLoadingWheel()
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
}