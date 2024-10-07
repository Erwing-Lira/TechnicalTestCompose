package com.example.technicaltest.home.repository.model

import java.util.Date

data class CardResponse(
    val id: String,
    val cvv: String,
    val number: String,
    val expiresDate: Date
)
