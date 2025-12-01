package com.mahshad.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mahshad.model.FavoriteArticle

@Entity(tableName = "favorite_articles")
data class FavoriteArticleEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "author")
    val author: String,
    @ColumnInfo(name = "published_at")
    val publishedAt: String,
    @ColumnInfo(name = "title")
    val title: String,
) {
    companion object {
        val DEFAULT = FavoriteArticleEntity(
            author = "unknown",
            publishedAt = "unknown",
            title = "unknown"
        )
    }
}

fun FavoriteArticleEntity.toFavoriteArticle() = FavoriteArticle(
    author = this.author,
    publishedAt = this.publishedAt,
    title = this.title
)