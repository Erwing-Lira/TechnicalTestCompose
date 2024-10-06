package com.example.technicaltest.home.usecase

import com.example.technicaltest.home.repository.IMovementsRepository
import com.example.technicaltest.home.state.Movement
import javax.inject.Inject

class FetchMovementsUseCase @Inject constructor(
    private val movementsRepository: IMovementsRepository
) {
    operator suspend fun invoke(): Map<String, List<Movement>> {
        var result: Map<String, List<Movement>> = mapOf()
        movementsRepository.getMovementsList().fold(
            onSuccess = { movementsList ->
                result = movementsList.sortedByDescending { movement ->
                    movement.date
                }.groupBy { movement ->
                    movement.date
                }
            },
            onFailure = {
            }
        )
        return result
    }
}