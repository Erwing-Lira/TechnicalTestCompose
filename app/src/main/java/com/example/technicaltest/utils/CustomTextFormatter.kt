package com.example.technicaltest.utils

fun String.maskedCVV(): String {
    return this.replace(Regex("\\d"), "*")
}

fun String.maskedCardNumber(): String {
    return this.replaceRange(0, 8, "**** **** ")
}