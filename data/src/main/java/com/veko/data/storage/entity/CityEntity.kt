package com.veko.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veko.domain.model.Weather

@Entity(tableName = "city_table")
data class CityEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "lon")
    val lon: Double,
    @ColumnInfo(name = "lat")
    val lat: Double,
    @Embedded
    val latestWeather: LatestWeatherEntity
)

fun CityEntity.toDomainModel() = Weather(
    city = latestWeather.city,
    temperature = latestWeather.temperature,
    feelsLike = latestWeather.feelsLike,
    pressure = latestWeather.pressure,
    humidity = latestWeather.humidity,
    visibility = latestWeather.visibility,
    windSpeed = latestWeather.windSpeed,
    weatherDescription = latestWeather.weatherDescription?.toDomainModel()
)