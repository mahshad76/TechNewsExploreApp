package com.mahshad.home.favorites

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mahshad.home.ui.HomeSearchBar
import com.mahshad.home.NewsFeedUiState
import com.mahshad.home.ui.SearchSuggestionsBox
import com.mahshad.model.Article
import com.mahshad.ui.components.TneLoadingWheel

@Composable
fun FavoriteNewsScreen(navigateToDetail: (Article) -> Unit) {
    val viewModel: FavoriteNewsScreenViewModel = hiltViewModel()
    val searchSuggestionState = viewModel.searchSuggestions.collectAsStateWithLifecycle()
    val searchQuery = viewModel.searchQueryStateFlow.collectAsStateWithLifecycle()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
    ) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        HomeSearchBar(
            modifier = Modifier.padding(bottom = 4.dp),
            searchQuery.value,
            { viewModel.updateSearchStateFlow(it) },
            {
                //navigateFromHomeToDetail(searchQuery.value)
            })
        when (searchSuggestionState.value) {
            is NewsFeedUiState.Loading -> TneLoadingWheel()
            is NewsFeedUiState.Error -> Toast.makeText(
                context, "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()

            is NewsFeedUiState.Successful -> {
                SearchSuggestionsBox(
                    (searchSuggestionState.value as NewsFeedUiState.Successful).news,
                    { article: Article ->
                        navigateToDetail(article)
                    },
                    { viewModel.bookmarkClicked(it) }
                )
            }
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
}
