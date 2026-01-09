package com.mahshad.model

data class FavoriteArticle(
    val author: String,
    val publishedAt: String,
    val title: String,
) {
    companion object {
        val DEFAULT = FavoriteArticle("1", "2", "3")
        val DEFAULT2 = FavoriteArticle(
            author = "unknown",
            publishedAt = "unknown",
            title = "unknown",
        )
    }
}
