package com.example.technicaltest.home.model

import com.example.technicaltest.home.state.MovementType
import kotlinx.serialization.Serializable

@Serializable
data class Movement(
    val id: String,
    val date: Long,
    val destination: String,
    val reference: String,
    val money: Double,
    val concept: String,
    val movementType: MovementType
)