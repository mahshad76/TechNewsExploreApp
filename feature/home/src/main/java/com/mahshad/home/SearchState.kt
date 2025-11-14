package com.mahshad.home

import com.mahshad.model.Article

sealed interface SearchState {
    data object Empty : SearchState
    data object Loading : SearchState
    data class Success(val searchResult: List<Article>) : SearchState
}