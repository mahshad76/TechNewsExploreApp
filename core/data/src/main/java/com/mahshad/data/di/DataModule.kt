package com.mahshad.data.di

import com.mahshad.data.repository.ArticleRepository
import com.mahshad.data.repository.AuthRepository
import com.mahshad.data.repository.DefaultArticleRepository
import com.mahshad.data.repository.DefaultAuthRepository
import com.mahshad.data.repository.DefaultFavoriteArticleRepository
import com.mahshad.data.repository.FavoriteArticleRepository
import com.mahshad.data.repository.UserDataRepository
import com.mahshad.data.repository.UserDataRepositoryDefault
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

    @Binds
    abstract fun bindAuthRepository(defaultAuthRepository: DefaultAuthRepository): AuthRepository

    @Binds
    abstract fun bindUserDataRepository(userDataRepositoryDefault: UserDataRepositoryDefault):
            UserDataRepository
}