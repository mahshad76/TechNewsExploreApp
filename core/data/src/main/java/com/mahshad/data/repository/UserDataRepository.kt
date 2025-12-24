package com.mahshad.data.repository

import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    fun getUserData(): Flow<List<String>>
    suspend fun postUserData(favoriteTopics: List<String>)
}