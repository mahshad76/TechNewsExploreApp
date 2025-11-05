package com.mahshad.data.repository

import com.mahshad.model.Article
import com.mahshad.network.TneNetworkDataSource
import com.mahshad.network.model.toArticle
import javax.inject.Inject

class DefaultArticleRepository @Inject constructor(
    private val tneNetworkDataSource:
    TneNetworkDataSource
) : ArticleRepository {
    override suspend fun getNews(): Result<List<Article>> = runCatching {
        val result = tneNetworkDataSource.getNews()
        result.map { networkArticle ->
            networkArticle.toArticle().getOrThrow()
        }
    }
}