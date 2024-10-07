package com.example.technicaltest.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun Date.formatExpiration(): String {
    val dateFormat = SimpleDateFormat("MM/yy", Locale("es", "MX"))
    return dateFormat.format(this)
}

fun LocalDate.convertMovementDate(): String {
    val dateFormat = DateTimeFormatter.ofPattern("dd/MMMM/yyyy", Locale("es", "MX"))
    return dateFormat.format(this)
}

fun Date.formatDate(): String {
    val dateFormat = SimpleDateFormat("MMMM d yyyy HH:mm", Locale("es", "MX"))
    return dateFormat.format(this)
}