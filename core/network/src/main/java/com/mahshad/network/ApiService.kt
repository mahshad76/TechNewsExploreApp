package com.mahshad.network

import com.mahshad.Dto.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getAppleOrTeslaNews(@Query("q") q: String): Response<NewsApiResponse>

    @GET("top-headlines")
    suspend fun getWorldNews(@Query("country") country: String): Response<NewsApiResponse>

    @GET("top-headlines")
    suspend fun getTechCrunchNews(
        @Query("sources") source: String
    ): Response<NewsApiResponse>

    @GET("top-headlines")
    suspend fun getWsjNews(
        @Query("domains") domains: String
    ): Response<NewsApiResponse>
}