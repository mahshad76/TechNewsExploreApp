package com.mahshad.datastore

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TnePreferencesDataSourceImpl @Inject constructor() :
    TnePreferencesDataSource {
    override fun getUserData(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun postUserData(favoriteTopics: List<String>) {
        TODO("Not yet implemented")
    }
}