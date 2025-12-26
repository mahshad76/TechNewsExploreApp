package com.mahshad.datasource.remotedatasource

import com.mahshad.Dto.NewsApiResponse
import com.mahshad.network.ApiService
import com.mahshad.threading.common.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTneNetworkDataSource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    TneNetworkDataSource {
    override suspend fun getAppleOrTeslaNews(q: String): Response<NewsApiResponse> =
        withContext(ioDispatcher) {
            return@withContext apiService.getAppleOrTeslaNews(q)
        }

    override suspend fun getWorldNews(country: String): Response<NewsApiResponse> =
        withContext(ioDispatcher) {
            return@withContext apiService.getWorldNews(country)
        }

    override suspend fun getTechCrunchNews(source: String): Response<NewsApiResponse> =
        withContext(ioDispatcher) {
            return@withContext apiService.getTechCrunchNews(source)
        }

    override suspend fun getWsjNews(domains: String): Response<NewsApiResponse> =
        withContext(ioDispatcher) {
            return@withContext apiService.getWsjNews(domains)
        }
}