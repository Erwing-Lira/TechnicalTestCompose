package com.example.technicaltest.signup.usecase

import javax.inject.Inject

class ValidateTextUseCase @Inject constructor() {

    companion object {
        const val TEXT_REGEX = "^[a-zA-Z]+$"
    }

    operator fun invoke(
        text: String
    ): Boolean {
        if (text.isEmpty()) {
            return true
        }
        return Regex(TEXT_REGEX).matches(text)
    }
}