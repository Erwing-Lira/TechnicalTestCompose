package com.example.technicaltest.home.model

import java.util.Date

data class CardInformation(
    val id: String = "",
    val cardNumber: String = "",
    val expiresDate: Date = Date(0),
    val cvv: String = ""
)