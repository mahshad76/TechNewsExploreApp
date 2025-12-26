package com.mahshad.data.repository

import com.mahshad.Dto.NewsApiResponse
import com.mahshad.Dto.toArticle
import com.mahshad.datasource.remotedatasource.TneNetworkDataSource
import com.mahshad.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class DefaultArticleRepository @Inject constructor(
    private val tneNetworkDataSource:
    TneNetworkDataSource
) : ArticleRepository {

    override fun getAppleOrTeslaNews(q: String): Flow<Result<List<Article>>> = flow {
        val response = tneNetworkDataSource.getAppleOrTeslaNews(q)
        emit(response.process())
    }

    override fun getWorldNews(country: String): Flow<Result<List<Article>>> = flow {
        val response = tneNetworkDataSource.getWorldNews(country)
        emit(response.process())
    }

    override fun getTechCrunchNews(source: String): Flow<Result<List<Article>>> = flow {
        val response = tneNetworkDataSource.getTechCrunchNews(source)
        emit(response.process())
    }

    override fun getWsjNews(domains: String): Flow<Result<List<Article>>> = flow {
        val response = tneNetworkDataSource.getWsjNews(domains)
        emit(response.process())
    }
}

fun Response<NewsApiResponse>.process(): Result<List<Article>> {
    return runCatching {
        val body = this.body()
        when (this.isSuccessful) {
            true -> body?.articles?.map { it.toArticle().getOrThrow() }
                ?: throw IllegalArgumentException("Response body is null")

            false -> throw IllegalArgumentException(this.errorBody().toString())
        }
    }
}