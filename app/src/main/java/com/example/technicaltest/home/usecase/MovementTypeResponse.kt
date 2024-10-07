package com.example.technicaltest.home.usecase

import com.example.technicaltest.home.state.MovementType

enum class MovementTypeResponse {
    INCOME,
    EXPENSE
}

fun convertToMovementType(movementType: String): MovementType {
    return when (movementType) {
        MovementTypeResponse.INCOME.name -> MovementType.Income
        else -> MovementType.Expense
    }
}