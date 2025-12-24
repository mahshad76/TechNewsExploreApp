package com.mahshad.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TnePreferencesDataSourceImpl @Inject constructor(
    private val preferenceDataStore:
    DataStore<Preferences>
) :
    TnePreferencesDataSource {
    override fun getUserData(): Flow<Set<String>> {
        TODO("Not yet implemented")
    }

    override suspend fun postUserData(favoriteTopics: List<String>) {
        TODO("Not yet implemented")
    }
}