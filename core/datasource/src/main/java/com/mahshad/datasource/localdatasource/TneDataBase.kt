package com.mahshad.datasource.localdatasource

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteArticleEntity::class], version = 2, exportSchema = true)
abstract class TneDataBase() : RoomDatabase() {
    abstract fun favoriteArticleDao(): FavoriteArticleDao
}