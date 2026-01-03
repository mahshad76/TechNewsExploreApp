package com.mahshad.model

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.mahshad.database.FavoriteArticleEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
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
        val ArticleNavType = object : NavType<Article>(isNullableAllowed = false) {
            override fun get(bundle: Bundle, key: String): Article? {
                return bundle.getString(key)?.let { Json.decodeFromString(it) }
            }

            override fun parseValue(value: String): Article {
                return Json.decodeFromString(Uri.decode(value))
            }

            override fun serializeAsValue(value: Article): String {
                return Uri.encode(Json.encodeToString(value))
            }

            override fun put(bundle: Bundle, key: String, value: Article) {
                bundle.putString(key, Json.encodeToString(value))
            }
        }
    }
}

fun Article.toEntity() = FavoriteArticleEntity(
    author = this.author,
    publishedAt = this.publishedAt,
    title = this.title
)
