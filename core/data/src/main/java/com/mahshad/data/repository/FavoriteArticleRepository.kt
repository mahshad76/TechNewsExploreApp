package com.mahshad.data.repository

import com.mahshad.model.FavoriteArticle
import kotlinx.coroutines.flow.Flow

interface FavoriteArticleRepository {
    fun getArticles(): Flow<List<FavoriteArticle>>

    suspend fun insert(article: FavoriteArticle)

    suspend fun delete(title: String, author: String)
}