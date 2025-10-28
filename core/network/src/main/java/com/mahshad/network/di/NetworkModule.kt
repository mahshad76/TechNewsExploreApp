package com.mahshad.network.di

import com.mahshad.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

private const val Key = BuildConfig.API_KEY

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
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
}
