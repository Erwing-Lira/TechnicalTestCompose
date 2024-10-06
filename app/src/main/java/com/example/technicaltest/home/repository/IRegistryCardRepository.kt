package com.example.technicaltest.home.repository

interface IRegistryCardRepository {
    suspend fun registryCard(): Result<Unit>
}