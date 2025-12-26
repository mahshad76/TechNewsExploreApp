package com.mahshad.datasource.remotedatasource

import com.mahshad.Dto.NewsApiResponse
import retrofit2.Response

interface TneNetworkDataSource {
    suspend fun getAppleNews(): Response<NewsApiResponse>
    suspend fun getTeslaNews(): Response<NewsApiResponse>
    suspend fun getUSNews(): Response<NewsApiResponse>
    suspend fun getTechCrunchNews(): Response<NewsApiResponse>
    suspend fun getWallStreetNews(): Response<NewsApiResponse>
}