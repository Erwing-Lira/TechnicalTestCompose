package com.example.technicaltest.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.formatAmount(): String {
    return NumberFormat.getInstance(Locale.US).format(this)
}