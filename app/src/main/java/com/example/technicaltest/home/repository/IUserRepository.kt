package com.example.technicaltest.home.repository

import com.example.technicaltest.home.state.CardState

interface IUserRepository {
    fun getUserInfo(): Result<CardState>
}