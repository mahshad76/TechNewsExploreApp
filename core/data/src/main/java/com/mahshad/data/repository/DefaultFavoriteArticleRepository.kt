package com.mahshad.data.repository

import com.mahshad.database.toFavoriteArticle
import com.mahshad.datasource.localdatasource.ArticleDataSource
import com.mahshad.model.Article
import com.mahshad.model.FavoriteArticle
import com.mahshad.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultFavoriteArticleRepository @Inject constructor(
    private val articleDataSource:
    ArticleDataSource
) :
    FavoriteArticleRepository {
    override fun getArticles(): Flow<List<FavoriteArticle>> =
        articleDataSource.getArticles().map { entities ->
            entities.map { it.toFavoriteArticle() }
        }

    override suspend fun insert(article: Article) {
        articleDataSource.insert(article = article.toEntity())
    }

    override suspend fun delete(title: String, author: String) =
        articleDataSource.delete(title, author)
}