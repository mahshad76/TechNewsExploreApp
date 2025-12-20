package com.mahshad.data.repository

interface AuthRepository {
    fun login(email: String, pass: String, onResult: (Boolean) -> Unit)
    fun signUp(email: String, pass: String, onResult: (Boolean) -> Unit)
}