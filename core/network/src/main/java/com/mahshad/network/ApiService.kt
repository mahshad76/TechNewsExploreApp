package com.mahshad.network

import com.mahshad.Dto.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") source: String = "techcrunch"
    ): Response<NewsApiResponse>
}