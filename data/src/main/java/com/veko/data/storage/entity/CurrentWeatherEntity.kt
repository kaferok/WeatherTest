package com.veko.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veko.domain.model.CurrentWeather

@Entity(tableName = "current_weather_table")
data class CurrentWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("date")
    val date: Long,
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

fun CurrentWeatherEntity.toDomainModel() = CurrentWeather(
    date = date,
    temperature = temperature,
    feelsLike = feelsLike,
    pressure = pressure,
    humidity = humidity,
    visibility = visibility,
    windSpeed = windSpeed,
    weatherDescription = weatherDescription?.toDomainModel()
)