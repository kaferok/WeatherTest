package com.veko.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils {
    companion object {
        private const val DAILY_WEATHER_DATE_FORMAT = "LLL dd"
        private const val CURRENT_WEATHER_DATE_FORMAT = "LLL dd HH:mm"
    }

    private val dailyWeatherDateFormatter =
        SimpleDateFormat(DAILY_WEATHER_DATE_FORMAT, Locale("ru", "RU"))

    private val currentWeatherDateFormatter = SimpleDateFormat(CURRENT_WEATHER_DATE_FORMAT, Locale("ru", "RU"))

    fun formatDailyWeather(time: Long, timeOffset: Long): String {
        val timeInMillis = (time +timeOffset).times(1000)
        val date = Date(timeInMillis)
        return dailyWeatherDateFormatter.format(date)
    }

    fun formatCurrentWeather(time: Long, timeOffset: Long): String{
        val timeInMillis = (time +timeOffset).times(1000)
        val date = Date(timeInMillis)
        return currentWeatherDateFormatter.format(date)
    }
}