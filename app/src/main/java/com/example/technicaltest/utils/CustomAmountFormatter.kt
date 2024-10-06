package com.example.technicaltest.utils

import java.text.DecimalFormat

fun Double.formatAmount(): String {
    val formatter = DecimalFormat("#,###.00")
    formatter.minimumFractionDigits = 2
    formatter.maximumFractionDigits = 2
    return formatter.format(this)
}