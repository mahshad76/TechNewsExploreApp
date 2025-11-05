package com.mahshad.data.repository

import com.mahshad.model.Article
import com.mahshad.network.TneNetworkDataSource
import com.mahshad.network.model.toArticle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultArticleRepository @Inject constructor(
    private val tneNetworkDataSource:
    TneNetworkDataSource
) : ArticleRepository {
    override fun getNews(): Flow<Result<List<Article>>> = flow {
        runCatching {
            val result = tneNetworkDataSource.getNews()
            result.map { networkArticle ->
                networkArticle.toArticle().getOrThrow()
            }
        }
    }
}