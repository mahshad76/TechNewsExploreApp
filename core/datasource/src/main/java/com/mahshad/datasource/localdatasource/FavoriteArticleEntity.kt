package com.mahshad.datasource.localdatasource

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("favorite_author")
    val author: String,
    @ColumnInfo("favorite_published_at")
    val publishedAt: String,
    @ColumnInfo("favorite_title")
    val title: String,
)