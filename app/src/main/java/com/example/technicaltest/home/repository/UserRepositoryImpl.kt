package com.example.technicaltest.home.repository

import com.example.technicaltest.home.state.CardState
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(): IUserRepository {
    override fun getUserInfo(): Result<CardState> {
        return Result.success(
            CardState(
                name = "Test 1",
                cardNumber = "1234 1234 1234",
                expiresDate = "10/10",
                cvv = "1234"
            )
        )
    }
}