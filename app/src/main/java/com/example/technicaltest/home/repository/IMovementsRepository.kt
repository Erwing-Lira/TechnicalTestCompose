package com.example.technicaltest.home.repository

import com.example.technicaltest.home.repository.model.MovementResponse

interface IMovementsRepository {
    suspend fun getMovementsList(cardId: String): Result<List<MovementResponse>>
}