package com.example.technicaltest.home.state

import com.example.technicaltest.home.model.Movement
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import java.time.LocalDate

data class MovementsState(
    val movementList: Map<LocalDate, List<Movement>> = mapOf(),
    val processState: ProcessState = ProcessState.Loading,
)

@Polymorphic
sealed interface MovementType {
    @Serializable
    data object Income: MovementType
    @Serializable
    data object Expense: MovementType
}