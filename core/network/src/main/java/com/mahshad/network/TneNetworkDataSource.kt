package com.mahshad.network

import com.mahshad.model.Article

interface TneNetworkDataSource {
    suspend fun getNews(): Result<List<Article>>
}