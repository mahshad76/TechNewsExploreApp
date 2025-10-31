package com.mahshad.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mahshad.network.BuildConfig
import com.mahshad.network.TneNetworkDataSource
import com.mahshad.network.model.NetworkArticle
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Retrofit API declaration for TNE Network API
 */

private interface ApiService {
    @GET("top-headlines")
    suspend fun getNews(
        @Query("sources") source: String = "techcrunch"
    ): Response<List<NetworkArticle>>
}

private const val TneBaseUrl = BuildConfig.BASE_URL

@Singleton
class RetrofitTneNetwork @Inject constructor(
    private val networkJson: Json,
    private val okhttpCallFactory: Call.Factory
) : TneNetworkDataSource {
    private val networkApi = Retrofit.Builder()
        .baseUrl(TneBaseUrl)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .build()
        .create(ApiService::class.java)

    override suspend fun getNews(): Response<List<NetworkArticle>> = networkApi.getNews()
}