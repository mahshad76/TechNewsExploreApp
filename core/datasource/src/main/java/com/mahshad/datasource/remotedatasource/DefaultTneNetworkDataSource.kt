package com.mahshad.datasource.remotedatasource

import com.mahshad.network.ApiService
import com.mahshad.threading.common.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTneNetworkDataSource @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    TneNetworkDataSource {
    override suspend fun getNews() = withContext(ioDispatcher) { apiService.getNews() }
}