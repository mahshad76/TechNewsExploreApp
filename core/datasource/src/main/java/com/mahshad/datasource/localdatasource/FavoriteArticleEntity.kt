package com.mahshad.datasource.localdatasource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteArticles")
data class FavoriteArticleEntity(
    @ColumnInfo(name = "favorite_author")
    val author: String,
    @ColumnInfo(name = "favorite_published_at")
    val publishedAt: String,
    @PrimaryKey
    @ColumnInfo(name = "favorite_title")
    val title: String,
)