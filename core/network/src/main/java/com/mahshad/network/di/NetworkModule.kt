package com.mahshad.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mahshad.network.ApiService
import com.mahshad.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private const val Key = BuildConfig.API_KEY

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        // Crucial for successful parsing: allows DTOs to ignore fields not defined in the DTO
        ignoreUnknownKeys = true
        // Allows the parser to handle null values gracefully if you have non-nullable fields
        // in your DTOs corresponding to nullable JSON keys.
        coerceInputValues = true
        // If your API uses snake_case (e.g., "total_results"), but your Kotlin uses camelCase (totalResults),
        // you should add the naming strategy or use @SerialName on every field.
        // For simplicity, we stick to ignoreUnknownKeys/coerceInputValues for the immediate fix.
    }

    // 2. Provide the Converter Factory, injecting the customized Json instance
    @Singleton
    @Provides
    fun provideConverterFactory(networkJson: Json): Converter.Factory {
        val contentType = "application/json".toMediaType()
        // Inject the customized Json instance here
        return networkJson.asConverterFactory(contentType)
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(
    ): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                }
            )
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalHttpUrl = originalRequest.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", Key)
                    .build()
                val newRequest = originalRequest.newBuilder()
                    .url(url)
                    .build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(
        okhttpCallFactory: Call.Factory,
        converter: Converter.Factory
    ): ApiService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .callFactory(okhttpCallFactory)
        .addConverterFactory(
            GsonConverterFactory.create()
        )
        .build()
        .create(ApiService::class.java)
}
