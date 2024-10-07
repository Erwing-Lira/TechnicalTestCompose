package com.example.technicaltest.signin.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticateRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
): IAuthenticateRepository {
    override suspend fun authenticate(
        email: String,
        password: String
    ): Result<FirebaseUser> {
        return try {
            val result = firebaseAuth
                .signInWithEmailAndPassword(
                    email,
                    password
                ).await()
            result.user?.let {
                Result.success(it)
            } ?: Result.failure(NullPointerException())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}