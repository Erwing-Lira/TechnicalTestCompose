package com.example.technicaltest.signin.usecase

import java.util.regex.Pattern
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor() {
    fun isValidEmail(
        pattern: Pattern,
        email: String
    ): Boolean {
        if (email.isEmpty()) {
            return true
        }
        return pattern.matcher(email).matches()
    }
}