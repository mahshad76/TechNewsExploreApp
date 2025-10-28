package com.mahshad.network.retrofit

import com.mahshad.network.model.NetworkArticle
import kotlinx.serialization.json.Json
import okhttp3.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

private interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") source: String = "techcrunch"
    ): Response<List<NetworkArticle>>
}

@Singleton
class RetrofitTneNetwork @Inject constructor(
    private val json: Json,
    private val okhttpCallFactory: Call.Factory
) {
}