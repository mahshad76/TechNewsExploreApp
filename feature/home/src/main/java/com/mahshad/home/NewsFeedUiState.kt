package com.mahshad.home

import com.mahshad.model.Article

sealed interface NewsFeedUiState<out T> {
    data class Successful(val news: List<Article>) : NewsFeedUiState<List<Article>>
    object Loading : NewsFeedUiState<Nothing>
    data class Error(val e: Throwable) : NewsFeedUiState<Nothing>
}