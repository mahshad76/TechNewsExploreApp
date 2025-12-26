package com.mahshad.datasource.remotedatasource

import com.mahshad.Dto.NewsApiResponse
import retrofit2.Response

interface TneNetworkDataSource {
    suspend fun getAppleOrTeslaNews(q: String): Response<NewsApiResponse>
    suspend fun getWorldNews(country: String): Response<NewsApiResponse>
    suspend fun getTechCrunchNews(source: String): Response<NewsApiResponse>
    suspend fun getWsjNews(domains: String): Response<NewsApiResponse>
}