package com.mahshad.network.model

import kotlinx.serialization.Serializable

/**
 * Network representation of [com.mahshad.model.Article]
 */
@Serializable
data class NetworkArticle(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val networkSource: NetworkSource,
    val title: String,
    val url: String,
    val urlToImage: String
)
