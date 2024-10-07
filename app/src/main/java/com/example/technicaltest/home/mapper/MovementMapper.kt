package com.example.technicaltest.home.mapper

import com.example.technicaltest.home.model.Movement
import com.example.technicaltest.home.repository.model.MovementResponse
import com.example.technicaltest.home.usecase.convertToMovementType

fun MovementResponse.toMovement(): Movement {
    return Movement(
        id = this.id,
        date = this.operationDate.toDate().time,
        destination = this.destination,
        reference = this.reference,
        movementType = convertToMovementType(this.movementType),
        money = this.money,
        concept = this.concept
    )
}