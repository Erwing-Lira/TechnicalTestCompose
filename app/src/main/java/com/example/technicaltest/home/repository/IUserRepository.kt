package com.example.technicaltest.home.repository

interface IUserRepository {
    suspend fun getUserInfo(): Result<String>
}