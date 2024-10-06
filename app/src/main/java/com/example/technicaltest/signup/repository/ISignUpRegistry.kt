package com.example.technicaltest.signup.repository

import android.net.Uri

interface ISignUpRegistry {
    suspend fun registry(
        name: String,
        lastName: String,
        email: String,
        password: String,
        photoUri: Uri
    ): Result<Unit>
}