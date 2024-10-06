package com.example.technicaltest.home.repository

import com.example.technicaltest.home.repository.model.CardResponse

interface ICardsRepository {
    suspend fun getFirstCard(): Result<CardResponse>
}