package com.mahshad.model

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String,
    val isLiked: Boolean = false
) {
    companion object {
        val DEFAULT = Article(
            author = "unknown",
            content = "unknown",
            description = "unknown",
            publishedAt = "unknown",
            source = Source.DEFAULT,
            title = "unknown",
            url = "unknown",
            urlToImage = "unknown",
            isLiked = false
        )
    }
}
