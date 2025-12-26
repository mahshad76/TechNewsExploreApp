package com.mahshad.ui

sealed interface UserDataState<out T> {
    data class Success(val favoriteTopics: Set<String>) : UserDataState<Set<String>>
    data class Error(val error: Throwable) : UserDataState<Nothing>
    data object Loading : UserDataState<Nothing>
}