package com.mahshad.network

import com.mahshad.network.model.NetworkArticle

interface TneNetworkDataSource {
    suspend fun getNews(): List<NetworkArticle>
}