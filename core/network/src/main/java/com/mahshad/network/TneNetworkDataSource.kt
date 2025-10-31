package com.mahshad.network

import com.mahshad.network.model.NetworkArticle
import retrofit2.Response

interface TneNetworkDataSource {
    suspend fun getNews(): Response<List<NetworkArticle>>
}