package com.example.technicaltest.home.state

import com.example.technicaltest.home.view.MovementType

data class Movement(
    val date: String,
    val destiny: String,
    val reference: String,
    val money: String,
    val movementType: MovementType
)