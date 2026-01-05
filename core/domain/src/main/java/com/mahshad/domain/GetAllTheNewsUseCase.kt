package com.mahshad.domain

import com.mahshad.model.Article
import kotlinx.coroutines.flow.StateFlow

interface GetAllTheNewsUseCase {
    val appleNews: StateFlow<ArticleFeedState<List<Article>>>
    val teslaNews: StateFlow<ArticleFeedState<List<Article>>>
    val worldNews: StateFlow<ArticleFeedState<List<Article>>>
    val techCrunchNews: StateFlow<ArticleFeedState<List<Article>>>
    val wsjNews: StateFlow<ArticleFeedState<List<Article>>>
}