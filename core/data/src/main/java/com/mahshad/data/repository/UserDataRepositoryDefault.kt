package com.mahshad.data.repository

import com.mahshad.datastore.TnePreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepositoryDefault @Inject constructor(
    private val tnePreferencesDataSource:
    TnePreferencesDataSource
) :
    UserDataRepository {
    override fun getUserData(): Flow<Set<String>> = tnePreferencesDataSource.getUserData()

    override suspend fun postUserData(favoriteTopics: List<String>) =
        tnePreferencesDataSource.postUserData(favoriteTopics)
}