package com.mahshad.network.model

import com.mahshad.model.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Network representation of [Article]
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

fun NetworkArticle.toArticle(): Result<Article> {
    return runCatching {
        val requiredTitle = this.title ?: throw IllegalArgumentException(
            "The title is required for " +
                    "each article and it is null for this item"
        )
        val requiredSource = this.networkSource?.toSource() ?: throw IllegalArgumentException(
            "The networkSource is required for " +
                    "each article and it is null for this item"
        )
        val source = requiredSource.getOrThrow()
        Article(
            author = this.author ?: "unKnown",
            content = this.content ?: "unKnown",
            description = this.description ?: "unKnown",
            publishedAt = this.publishedAt ?: "unKnown",
            source = source,
            title = requiredTitle,
            url = this.url ?: "unKnown",
            urlToImage = this.urlToImage ?: "unKnown"
        )
    }
}
