package com.example.technicaltest.utils

import com.example.technicaltest.home.state.MovementType
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

val movementTypeModule = SerializersModule {
    polymorphic(MovementType::class) {
        subclass(MovementType.Income::class)
        subclass(MovementType.Expense::class)
    }
}

val json = Json {
    serializersModule = movementTypeModule
    classDiscriminator = "type"
}