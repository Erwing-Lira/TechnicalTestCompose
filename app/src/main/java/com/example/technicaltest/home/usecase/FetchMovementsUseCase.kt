package com.example.technicaltest.home.usecase

import com.example.technicaltest.home.mapper.toMovement
import com.example.technicaltest.home.model.Movement
import com.example.technicaltest.home.repository.IMovementsRepository
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