package com.example.technicaltest.signin.repository

import com.google.firebase.auth.FirebaseUser

interface IAuthenticateRepository {
    suspend fun authenticate(email: String, password: String): Result<FirebaseUser>
}