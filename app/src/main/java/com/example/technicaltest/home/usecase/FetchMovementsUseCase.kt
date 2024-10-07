package com.example.technicaltest.home.usecase

import com.example.technicaltest.home.mapper.toMovement
import com.example.technicaltest.home.model.Movement
import com.example.technicaltest.home.repository.IMovementsRepository
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class FetchMovementsUseCase @Inject constructor(
    private val movementsRepository: IMovementsRepository
) {
    suspend operator fun invoke(
        cardId: String
    ): Map<LocalDate, List<Movement>>? {
        var result: Map<LocalDate, List<Movement>>? = null
        movementsRepository.getMovementsList(cardId).fold(
            onSuccess = { movementsList ->
                val movementList = movementsList.map { movement ->
                    movement.toMovement()
                }
                result = movementList.sortedByDescending { movement ->
                    movement.date
                }.groupBy { movement ->
                    Instant.ofEpochMilli(movement.date)
                        .atZone(ZoneId.of("UTC-6"))
                        .toLocalDate()
                }
            },
            onFailure = {
                result = null
            }
        )
        return result
    }
}