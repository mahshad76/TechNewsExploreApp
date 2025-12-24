package com.mahshad.data.repository

import com.mahshad.datastore.TnePreferencesDataSourceImpl
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryDefault @Inject constructor(
    private val tnePreferencesDataSourceImpl:
    TnePreferencesDataSourceImpl
) :
    UserDataRepository {
    override fun getUserData(): Flow<List<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun postUserData(favoriteTopics: List<String>) {
        TODO("Not yet implemented")
    }
}