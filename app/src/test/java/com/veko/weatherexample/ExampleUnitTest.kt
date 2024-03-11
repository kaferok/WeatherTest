package com.veko.weatherexample

import com.veko.domain.utils.DateUtils
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DateUnitTest {
    private val dateUtils = DateUtils()

    @Test
    fun check_date_formatter() {
        val timeMillis = 1710154800L
        val formattedDate = dateUtils.formatDailyWeather(timeMillis, -18000)
        assertEquals("март 11", formattedDate)
    }
}