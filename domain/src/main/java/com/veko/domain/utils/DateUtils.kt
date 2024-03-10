package com.veko.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        private const val OPERATIONS_DATE_FORMAT = "LLL dd"
    }

    private val dateFormatter =
        SimpleDateFormat(OPERATIONS_DATE_FORMAT, Locale("ru", "RU"))

    fun formatOperation(time: Long, timeOffset: Long): String {
        val date = Date(time + timeOffset)
        return dateFormatter.format(date)
    }
}