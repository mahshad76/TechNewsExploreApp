package com.mahshad.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun NewsScreen(
    //navigateFromHomeToDetail: (String) -> Unit,
    newsScreenViewModel: NewsScreenViewModel = hiltViewModel()
) {
    val newsFeed = newsScreenViewModel.mergedFlow.collectAsStateWithLifecycle()
    val searchQuery = newsScreenViewModel._searchQueryStateFlow.collectAsStateWithLifecycle()
    val searchSuggestion = newsScreenViewModel.searchSuggestions.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(10.dp)) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        HomeSearchBar(
            modifier = Modifier.padding(bottom = 4.dp),
            searchQuery.value,
            { newsScreenViewModel.updateSearchQueryFlow(it) },
            {
                //navigateFromHomeToDetail(searchQuery.value)
            })
        if (!searchQuery.value.isEmpty()) {
            SearchSuggestionsBox(searchSuggestion.value.map { it.title }) {
                newsScreenViewModel.updateSearchQueryFlow(
                    it
                )
            }
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
}