package com.mahshad.datasource.localdatasource

import com.mahshad.database.FavoriteArticleEntity
import kotlinx.coroutines.flow.Flow

interface ArticleDataSource {
    fun getArticles(): Flow<List<FavoriteArticleEntity>>

    suspend fun insert(article: FavoriteArticleEntity)

    suspend fun delete(title: String, author: String)
}