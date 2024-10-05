package com.example.technicaltest.signin.usecase

import javax.inject.Inject

class ValidatePassUseCase @Inject constructor() {
    fun isValidPassword(
        password: String
    ): Boolean {
        if (password.isEmpty()) {
            return true
        }
        return password.length > 6
    }
}