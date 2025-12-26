package com.mahshad.data.repository

import com.mahshad.Dto.toArticle
import com.mahshad.datasource.remotedatasource.TneNetworkDataSource
import com.mahshad.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultArticleRepository @Inject constructor(
    private val tneNetworkDataSource:
    TneNetworkDataSource
) : ArticleRepository {
    override fun getNews(query: String): Flow<Result<List<Article>>> = flow {
        val result = runCatching {
            val response = tneNetworkDataSource.getNews(query)
            val body = response.body()
            when (response.isSuccessful) {
                true -> body?.articles?.map { it.toArticle().getOrThrow() }
                    ?: throw IllegalArgumentException("Response body is null")

                false -> throw IllegalArgumentException(response.errorBody().toString())
            }
        }
        emit(result)
    }
}