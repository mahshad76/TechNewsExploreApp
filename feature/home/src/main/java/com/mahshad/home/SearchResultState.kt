package com.mahshad.home

import com.mahshad.model.Article

sealed interface SearchResultState {
    data object Empty : SearchResultState
    data object Loading : SearchResultState
    data class Success(val searchResult: List<Article>) : SearchResultState
}