package com.example.technicaltest.home.repository.model

import com.google.firebase.Timestamp

data class MovementResponse(
    val id: String,
    val concept: String,
    val destination: String,
    val movementType: String,
    val operationDate: Timestamp,
    val reference: String,
    val money: Double
)
