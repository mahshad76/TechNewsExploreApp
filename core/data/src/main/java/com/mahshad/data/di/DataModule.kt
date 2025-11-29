package com.mahshad.data.di

import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.DefaultArticleRepository
import com.mahshad.data.repository.DefaultFavoriteArticleRepository
import com.mahshad.data.repository.FavoriteArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindArticleRepository(defaultArticleRepository: DefaultArticleRepository):
            ArticleRepository

    @Binds
    abstract fun bindFavoriteArticleRepository(
        defaultFavoriteArticleRepository:
        DefaultFavoriteArticleRepository
    ): FavoriteArticleRepository
}