package com.mahshad.data.repository

import com.mahshad.datasource.localdatasource.ArticleDataSource
import com.mahshad.model.FavoriteArticle
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultFavoriteArticleRepository @Inject constructor(
    private val articleDataSource:
    ArticleDataSource
) :
    FavoriteArticleRepository {
    override fun getArticles(): Flow<List<FavoriteArticle>> {
        TODO("Not yet implemented")
    }

    override suspend fun insert(article: FavoriteArticle) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(title: String, author: String) {
        TODO("Not yet implemented")
    }
}