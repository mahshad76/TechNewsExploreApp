package com.mahshad.ui

import com.mahshad.model.Article

sealed interface NewsFeed<out T> {
    data class Successful(val news: List<Article>) : NewsFeed<List<Article>>
    object Loading : NewsFeed<Nothing>
    data class Error(val e: Throwable) : NewsFeed<Nothing>
}