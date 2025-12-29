package com.mahshad.home

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
import com.mahshad.ui.NewsFeed
import com.mahshad.ui.components.TneLoadingWheel

@Composable
fun NewsScreen(
    //navigateFromHomeToDetail: (String) -> Unit,
    newsScreenViewModel: NewsScreenViewModel = hiltViewModel()
) {
//    val newsFeed = newsScreenViewModel.mergedFlow.collectAsStateWithLifecycle()
    val searchQuery = newsScreenViewModel._searchQueryStateFlow.collectAsStateWithLifecycle()
    val searchSuggestion = newsScreenViewModel.searchSuggestions.collectAsStateWithLifecycle()
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
            { newsScreenViewModel.updateSearchQueryFlow(it) },
            {
                //navigateFromHomeToDetail(searchQuery.value)
            })
        val searchSuggestionValue = searchSuggestion.value
        when (searchSuggestionValue) {
            is NewsFeed.Successful -> {
                SearchSuggestionsBox(
                    searchSuggestionValue.news.map { it.title to it.urlToImage }) {
                    newsScreenViewModel.updateSearchQueryFlow(
                        it
                    )
                }
            }

            is NewsFeed.Error -> Toast.makeText(
                context, "Something went wrong",
                Toast.LENGTH_SHORT
            ).show()

            is NewsFeed.Loading -> TneLoadingWheel()
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
}