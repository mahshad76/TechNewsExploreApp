package com.mahshad.domain.di

import com.mahshad.domain.GetAllTheNewsUseCase
import com.mahshad.domain.GetAllTheNewsUseCaseImpl
import com.mahshad.threading.common.DefaultDispatcher
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindGetAllNewsUseCase(getAllTheNewsUseCaseImpl: GetAllTheNewsUseCaseImpl):
            GetAllTheNewsUseCase

    companion object {
        @Provides
        @Singleton
        fun provideAppScope(@DefaultDispatcher defaultDispatcher: CoroutineDispatcher): CoroutineScope =
            CoroutineScope(
                SupervisorJob() + defaultDispatcher
            )
    }
}