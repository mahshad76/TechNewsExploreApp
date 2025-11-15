package com.mahshad.home

import com.mahshad.model.Article

sealed interface SearchState {
    data class Empty(val articles: List<Article>) : SearchState
    data object Loading : SearchState
    data class Success(val searchResult: List<Article>) : SearchState
}