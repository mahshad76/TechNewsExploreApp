package com.mahshad.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {
    @Query("SELECT * FROM favorite_articles")
    fun getArticles(): Flow<List<FavoriteArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: FavoriteArticleEntity)

    @Query("DELETE FROM favorite_articles WHERE title = :title AND author = :author")
    suspend fun delete(title: String, author: String)
}