package com.mahshad.data.repository

import com.mahshad.model.Article
import com.mahshad.model.FavoriteArticle
import kotlinx.coroutines.flow.Flow

interface FavoriteArticleRepository {
    fun getArticles(): Flow<List<FavoriteArticle>>

    suspend fun insert(article: Article)

    suspend fun delete(title: String, author: String)
}