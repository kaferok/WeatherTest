package com.veko.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "latest_weather_table")
data class LatestWeatherEntity(
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "temperature")
    val temperature: Double,
    @ColumnInfo(name = "feels_like")
    val feelsLike: Double,
    @ColumnInfo(name = "pressure")
    val pressure: Int,
    @ColumnInfo(name = "humidity")
    val humidity: Int,
    @ColumnInfo(name = "visibility")
    val visibility: Int,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double,
    @Embedded
    val weatherDescription: CommonWeatherEntity?
)