package com.mahshad.data.repository

import com.mahshad.network.TneNetworkDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(private val tneNetworkDataSource: TneNetworkDataSource) {

}