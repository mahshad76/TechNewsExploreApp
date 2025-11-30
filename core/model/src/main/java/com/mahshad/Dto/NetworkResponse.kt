package com.mahshad.Dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsApiResponse(
    @SerialName("status")
    val status: String,
    @SerialName("totalResults")
    val totalResults: Int,
    @SerialName("articles")
    val articles: List<NetworkArticle>
) {
    companion object {
        val DEFAULT = NewsApiResponse(
            "unknown",
            1,
            listOf(NetworkArticle.DEFAULT)
        )
    }
}
