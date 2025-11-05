package com.mahshad.data.di

import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.DefaultArticleRepository
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
}