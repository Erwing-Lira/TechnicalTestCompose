package com.example.technicaltest.signup.util

import com.google.firebase.Timestamp
import java.util.Calendar
import java.util.UUID
import kotlin.random.Random

data class CardFake(
    val number: String,
    val expiresDate: Timestamp,
    val cvv: String
)

val getFakeCard = CardFake(
    number = "123412341234",
    expiresDate = Timestamp(getDate().time),
    cvv = "1234"
)

fun getDate(): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(2027, Calendar.FEBRUARY, 24)
    return calendar
}

data class MovementFake(
    val reference: String,
    val destination: String,
    val operationDate: Timestamp,
    val movementType: String,
    val concept: String,
    val money: Double
)

val getMovementsList = listOf(
    MovementFake(
        reference = UUID.randomUUID().toString(),
        destination = "Paco",
        operationDate = Timestamp(randomDate(2024, Calendar.OCTOBER, 4).time),
        movementType = MovementTypeFake.INCOME.name,
        concept = "Prueba",
        money = 794.00
    ),
    MovementFake(
        reference = UUID.randomUUID().toString(),
        destination = "Juan",
        operationDate = Timestamp(randomDate(2024, Calendar.OCTOBER, 2).time),
        movementType = MovementTypeFake.INCOME.name,
        concept = "Prueba",
        money = 70994.00
    ),
    MovementFake(
        reference = UUID.randomUUID().toString(),
        destination = "Juan",
        operationDate = Timestamp(randomDate(2024, Calendar.SEPTEMBER, 18).time),
        movementType = MovementTypeFake.EXPENSE.name,
        concept = "Prueba",
        money = 90.00
    ),
    MovementFake(
        reference = UUID.randomUUID().toString(),
        destination = "Pancho",
        operationDate = Timestamp(randomDate(2024, Calendar.OCTOBER, 2).time),
        movementType = MovementTypeFake.INCOME.name,
        concept = "Prueba",
        money = 38794.00
    ),
    MovementFake(
        reference = UUID.randomUUID().toString(),
        destination = "Julia",
        operationDate = Timestamp(randomDate(2024, Calendar.SEPTEMBER, 30).time),
        movementType = MovementTypeFake.EXPENSE.name,
        concept = "Prueba",
        money = 180.60
    ),
    MovementFake(
        reference = UUID.randomUUID().toString(),
        destination = "Julia",
        operationDate = Timestamp(randomDate(2024, Calendar.SEPTEMBER, 18).time),
        movementType = MovementTypeFake.INCOME.name,
        concept = "Prueba",
        money = 456.84
    ),
    MovementFake(
        reference = UUID.randomUUID().toString(),
        destination = "Oxxo",
        operationDate = Timestamp(randomDate(2024, Calendar.SEPTEMBER, 18).time),
        movementType = MovementTypeFake.EXPENSE.name,
        concept = "Prueba",
        money = 37543.00
    ),
    MovementFake(
        reference = UUID.randomUUID().toString(),
        destination = "Paco",
        operationDate = Timestamp(randomDate(2024, Calendar.SEPTEMBER, 16).time),
        movementType = MovementTypeFake.INCOME.name,
        concept = "Prueba",
        money = 567.00
    )
)

fun randomDate(year: Int, month: Int, day: Int): Calendar {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)

    val randomHour = Random.nextInt(0, 24)
    val randomMinute = Random.nextInt(0, 60)
    val randomSecond = Random.nextInt(0, 60)

    calendar.set(Calendar.HOUR_OF_DAY, randomHour)
    calendar.set(Calendar.MINUTE, randomMinute)
    calendar.set(Calendar.SECOND, randomSecond)

    return calendar
}

private enum class MovementTypeFake {
    INCOME,
    EXPENSE
}