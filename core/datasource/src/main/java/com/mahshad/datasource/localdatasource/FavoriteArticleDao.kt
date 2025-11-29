package com.mahshad.datasource.localdatasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteArticleDao {
    @Query("SELECT * FROM FavoriteArticles")
    fun getArticles(): Flow<List<FavoriteArticleEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mobileObjects: List<FavoriteArticleEntity>)

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insert(article: FavoriteArticleEntity)
//
//    @Query(
//        "DELETE FROM FavoriteArticles WHERE favorite_title = :title AND " +
//                "favorite_author = :author"
//    )
//    suspend fun delete(title: String, author: String): Int
}