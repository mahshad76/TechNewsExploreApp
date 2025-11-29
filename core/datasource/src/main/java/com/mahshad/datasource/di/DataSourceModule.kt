package com.mahshad.datasource.di

import android.content.Context
import androidx.room.Room
import com.mahshad.datasource.localdatasource.TneDataBase
import com.mahshad.datasource.remotedatasource.DefaultTneNetworkDataSource
import com.mahshad.datasource.remotedatasource.TneNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindTneNetworkDataSource(defaultTneNetworkDataSource: DefaultTneNetworkDataSource):
            TneNetworkDataSource

    companion object {
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
}