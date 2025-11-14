package com.mahshad.datasource.di

import com.mahshad.datasource.remotedatasource.DefaultTneNetworkDataSource
import com.mahshad.datasource.remotedatasource.TneNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindTneNetworkDataSource(defaultTneNetworkDataSource: DefaultTneNetworkDataSource):
            TneNetworkDataSource
}