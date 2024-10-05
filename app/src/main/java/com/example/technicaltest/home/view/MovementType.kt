package com.example.technicaltest.home.view

sealed interface MovementType {
    data object Income: MovementType
    data object Outgoing: MovementType
}