package com.mahshad.ui

import com.mahshad.model.Article

sealed interface NewsFeed {
    data class Successful(val news: List<Article>) : NewsFeed
    object Loading : NewsFeed
    data class Error(val e: Exception) : NewsFeed
}