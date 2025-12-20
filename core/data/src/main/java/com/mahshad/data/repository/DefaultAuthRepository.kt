package com.mahshad.data.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class DefaultAuthRepository @Inject constructor() : AuthRepository {
    private val auth = FirebaseAuth.getInstance()

    override fun login(email: String, pass: String, onResult: (Boolean) -> Unit) {
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { onResult(it.isSuccessful) }
    }

    override fun signUp(email: String, pass: String, onResult: (Boolean) -> Unit) {
        auth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener { onResult(it.isSuccessful) }
    }
}