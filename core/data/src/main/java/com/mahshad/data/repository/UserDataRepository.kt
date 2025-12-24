package com.mahshad.data.repository

import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    fun getUserData(): Flow<Set<String>>
    suspend fun postUserData(favoriteTopics: List<String>)
}