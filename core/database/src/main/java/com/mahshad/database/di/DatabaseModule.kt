package com.mahshad.database.di

import android.content.Context
import androidx.room.Room
import com.mahshad.database.TneDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideTneDataBase(@ApplicationContext context: Context): TneDataBase =
        Room.databaseBuilder(
            context,
            TneDataBase::class.java,
            TneDataBase.DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideFavoriteArticleDao(tneDataBase: TneDataBase) = tneDataBase.favoriteArticleDao()
}