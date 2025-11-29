package com.mahshad.datasource.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mahshad.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {
    @Query("SELECT * FROM FavoriteArticleEntity")
    fun getArticles(): Flow<List<Article>>

    @Insert
    suspend fun insert(article: FavoriteArticleEntity)

    @Query("DELETE FROM FavoriteArticleEntity WHERE favorite_title = :title AND " +
            "favorite_author = :author")
    suspend fun delete(title: String, author: String)
}