package com.mahshad.Dto

import com.mahshad.model.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.Result.Companion.success

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
    @SerialName("source")
    val networkSource: NetworkSource?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?,
    @SerialName("urlToImage")
    val urlToImage: String?
) {
    companion object {
        val DEFAULT = NetworkArticle(
            author = "unknown",
            content = "unknown",
            description = "unknown",
            publishedAt = "unknown",
            title = "unknown",
            url = "unknown",
            urlToImage = "unknown",
            networkSource = NetworkSource.DEFAULT
        )
    }
}

fun NetworkArticle.toArticle(): Result<Article> {
    return runCatching {
        val requiredTitle = this.title ?: throw IllegalArgumentException(
            "The title is required for " +
                    "each article and it is null for this item"
        )
        val requiredSource =
            this.networkSource?.toSource() ?: success(
                NetworkSource.DEFAULT.toSource()
                    .getOrThrow()
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
