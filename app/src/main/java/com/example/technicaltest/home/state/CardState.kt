package com.example.technicaltest.home.state

import com.example.technicaltest.home.model.CardInformation

data class CardState(
    val cardInformation: CardInformation = CardInformation(),
    val processState: ProcessState = ProcessState.Loading,
)