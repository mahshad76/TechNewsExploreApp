package com.mahshad.home

import com.mahshad.model.Article

sealed interface SearchResultUiState {
    //    only deciding based on the news feed state
    data object EmptyQuery : SearchResultUiState

    //    there is no any match between the search query and the title of the articles
    data object Empty : SearchResultUiState

    //    result of the search comparison is not ready
    data object Loading : SearchResultUiState

    //    result of the search comparison is ready and not empty
    data class Success(val searchResult: List<Article>) : SearchResultUiState
}