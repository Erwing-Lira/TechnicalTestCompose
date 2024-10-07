package com.example.technicaltest.home.usecase

import com.example.technicaltest.home.model.Movement
import com.example.technicaltest.home.repository.IMovementsRepository
import com.example.technicaltest.home.repository.model.MovementResponse
import com.example.technicaltest.home.state.MovementType
import java.util.Date
import javax.inject.Inject

class FetchMovementsUseCase @Inject constructor(
    private val movementsRepository: IMovementsRepository
) {
    suspend operator fun invoke(
        cardId: String
    ): Map<Date, List<Movement>>? {
        var result: Map<Date, List<Movement>>? = null
        movementsRepository.getMovementsList(cardId).fold(
            onSuccess = { movementsList ->
                val movementList = movementsList.map { movement ->
                    movement.toMovement()
                }
                result = movementList.sortedByDescending { movement ->
                    movement.date
                }.groupBy { movement ->
                    Date(movement.date)
                }
            },
            onFailure = {
                result = null
            }
        )
        return result
    }
}

private fun MovementResponse.toMovement(): Movement {
    return Movement(
        id = this.id,
        date = this.operationDate.time,
        destination = this.destination,
        reference = this.reference,
        movementType = convertToMovementType(this.movementType),
        money = this.money,
        concept = this.concept
    )
}

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