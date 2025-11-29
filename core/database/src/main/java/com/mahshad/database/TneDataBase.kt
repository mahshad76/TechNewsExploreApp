package com.mahshad.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteArticleEntity::class], version = 2, exportSchema = true)
abstract class TneDataBase() : RoomDatabase() {
    abstract fun favoriteArticleDao(): FavoriteArticleDao
    companion object{
        val DATABASE_NAME = "articles_db"
    }
}