package com.mahshad.data.repository

import com.mahshad.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {
    fun getNews(query: String): Flow<Result<List<Article>>>
}