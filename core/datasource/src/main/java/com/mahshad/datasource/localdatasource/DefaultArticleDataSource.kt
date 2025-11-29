package com.mahshad.datasource.localdatasource

import com.mahshad.database.FavoriteArticleDao
import com.mahshad.database.FavoriteArticleEntity
import com.mahshad.threading.common.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultArticleDataSource @Inject constructor(
    private val dao: FavoriteArticleDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    ArticleDataSource {
    override fun getArticles(): Flow<List<FavoriteArticleEntity>> =
        dao.getArticles().flowOn(ioDispatcher)

    override suspend fun insert(article: FavoriteArticleEntity) =
        withContext(ioDispatcher) { dao.insert(article) }

    override suspend fun delete(title: String, author: String) =
        withContext(ioDispatcher) {
            dao.delete(title, author)
        }
}