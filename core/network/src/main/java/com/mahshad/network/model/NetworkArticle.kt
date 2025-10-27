package com.mahshad.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [com.mahshad.model.Article]
 */
@Serializable
data class NetworkArticle(
    @SerialName("author")
    val author: String?,
    @SerialName("content")
    val content: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("publishedAt")
    val publishedAt: String?,
    @SerialName("networkSource")
    val networkSource: NetworkSource?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("urlToImage")
    val urlToImage: String?
)
