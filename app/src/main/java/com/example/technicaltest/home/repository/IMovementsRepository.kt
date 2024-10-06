package com.example.technicaltest.home.repository

import com.example.technicaltest.home.state.Movement

interface IMovementsRepository {
    fun getMovementsList(): Result<List<Movement>>
}