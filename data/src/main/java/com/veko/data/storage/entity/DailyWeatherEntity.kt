package com.veko.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veko.domain.model.DailyWeather

@Entity(tableName = "daily_weather_table")
data class DailyWeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("date")
    val date: Long,
    @Embedded
    val temperature: TemperatureEntity,
    @Embedded(prefix = "feels_like_")
    val feelsLike: TemperatureEntity,
    @ColumnInfo(name = "pressure")
    val pressure: Int,
    @ColumnInfo(name = "humidity")
    val humidity: Int,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double,
    @Embedded
    val weatherDescription: CommonWeatherEntity?
)

fun DailyWeatherEntity.toDomainModel() = DailyWeather(
    date = date,
    temperature = temperature.toDomainModel(),
    feelsLike = feelsLike.toDomainModel(),
    pressure = pressure,
    humidity = humidity,
    windSpeed = windSpeed,
    weatherDescription = weatherDescription?.toDomainModel()
)