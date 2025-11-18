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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.serialization.Serializable

@Serializable
data object HomeScreenRoute

@Composable
fun HomeScreen(homeScreenViewModel: HomeScreenViewModel = hiltViewModel()) {

    val newsFeed = homeScreenViewModel.feedState.collectAsStateWithLifecycle()
    val searchQuery = homeScreenViewModel._searchQueryStateFlow.collectAsStateWithLifecycle()
    val searchSuggestion = homeScreenViewModel.searchSuggestions.collectAsStateWithLifecycle()

    Column(modifier = Modifier.padding(10.dp)) {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        HomeSearchBar(
            modifier = Modifier.padding(bottom = 4.dp),
            searchQuery.value,
            { homeScreenViewModel.updateSearchQueryFlow(it) },
            {})
        if (!searchQuery.value.isEmpty()) {
            SearchSuggestionsBox(searchSuggestion.value.map { it.title }) {
                homeScreenViewModel.updateSearchQueryFlow(
                    it
                )
            }
        }
        // if the search query is not empty and nothing is selected yet show the suggestions
//    when (newsFeed.value) {
//        is NewsFeed.Successful -> {
//            Log.d("TAG", "HomeScreen success")
//            Text((newsFeed.value as NewsFeed.Successful).news[0].content)
//        }
//
//        is NewsFeed.Error -> {
//            Log.d("TAG", "Error:${(newsFeed.value as NewsFeed.Error).e.toString()}")
//        }
//
//        is NewsFeed.Loading -> {
//            Log.d("TAG", "HomeScreen: loading")
//            CircularProgressIndicator(
//                modifier = Modifier.size(64.dp),
//                color = androidx.compose.ui.graphics.Color(Color.BLACK),
//                strokeWidth = 6.dp
//            )
//        }
//    }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))
    }
}

@Composable
@Preview
fun Previeww() {
    HomeScreen()
}