package com.mahshad.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TnePreferencesDataSourceImpl @Inject constructor(
    private val preferenceDataStore:
    DataStore<Preferences>
) :
    TnePreferencesDataSource {
    companion object {
        val RECENT_FAVORITES = stringSetPreferencesKey("recent_favorites")
    }

    override fun getUserData(): Flow<Set<String>> =
        preferenceDataStore.data
            .map { preferences ->
                preferences[RECENT_FAVORITES] ?: emptySet()
            }

    override suspend fun postUserData(favoriteTopics: Set<String>) {
        preferenceDataStore.edit { preferences ->
            preferences[RECENT_FAVORITES] = favoriteTopics
        }
    }
}