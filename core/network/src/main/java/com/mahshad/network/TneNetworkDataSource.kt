package com.mahshad.network

import com.mahshad.model.Article
import retrofit2.Response

interface TneNetworkDataSource {
    suspend fun getNews(): Response<List<Article>>
}