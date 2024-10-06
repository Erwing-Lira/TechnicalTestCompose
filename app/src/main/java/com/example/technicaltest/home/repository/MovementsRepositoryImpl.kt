package com.example.technicaltest.home.repository

import com.example.technicaltest.home.state.Movement
import com.example.technicaltest.home.view.MovementType
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class MovementsRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): IMovementsRepository {
    override fun getMovementsList(): Result<List<Movement>> {
        return Result.success(getList())
    }
}

fun getList() = listOf(
    Movement(
        "5/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "900.00",
        MovementType.Income
    ),
    Movement(
        "5/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "80.00",
        MovementType.Outgoing
    ),
    Movement(
        "2/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "1,000.00",
        MovementType.Income
    ),
    Movement(
        "4/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "4,800.00",
        MovementType.Income
    ),
    Movement(
        "2/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "458.00",
        MovementType.Outgoing
    ),
    Movement(
        "4/5/24",
        "Test 1",
        "87546tgvtyubhn",
        "99.00",
        MovementType.Income
    )
)