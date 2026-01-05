package com.mahshad.domain

import com.mahshad.model.Article

sealed interface ArticleFeedState<out T> {
    data object Loading : ArticleFeedState<Nothing>
    data class Success(val articles: List<Article>) : ArticleFeedState<List<Article>>
    data class Error(val cause: Throwable) : ArticleFeedState<Nothing>
}
