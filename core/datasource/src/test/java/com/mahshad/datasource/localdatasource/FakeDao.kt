package com.mahshad.datasource.localdatasource

import com.mahshad.database.FavoriteArticleDao
import com.mahshad.database.FavoriteArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeDao : FavoriteArticleDao {
    override fun getArticles(): Flow<List<FavoriteArticleEntity>> = flow {
        emit(listOf(FavoriteArticleEntity.DEFAULT))
    }

    override suspend fun insert(article: FavoriteArticleEntity) {}

    override suspend fun delete(title: String, author: String) {}
}