package com.mahshad.datasource.remotedatasource

import com.mahshad.Dto.NewsApiResponse
import retrofit2.Response

interface TneNetworkDataSource {
    suspend fun getNews(): Response<NewsApiResponse>
}