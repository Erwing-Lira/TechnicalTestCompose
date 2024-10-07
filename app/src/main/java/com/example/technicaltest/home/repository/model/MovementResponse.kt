package com.example.technicaltest.home.repository.model

import java.util.Date

data class MovementResponse(
    val id: String,
    val concept: String,
    val destination: String,
    val movementType: String,
    val operationDate: Date,
    val reference: String,
    val money: Double
)
