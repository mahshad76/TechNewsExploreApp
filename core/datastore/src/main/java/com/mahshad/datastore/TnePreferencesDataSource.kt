package com.mahshad.datastore

import kotlinx.coroutines.flow.Flow

interface TnePreferencesDataSource {
    fun getUserData(): Flow<Set<String>>
    suspend fun postUserData(favoriteTopics: Set<String>)
}