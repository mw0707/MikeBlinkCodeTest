package com.example.mikeblinkcodetest.base.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtil {

    fun parseTimestamp(timestamp: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        return LocalDateTime.parse(timestamp, formatter)
    }

    fun getCurrentTimestamp(): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        return currentDateTime.format(formatter)
    }
}