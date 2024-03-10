package com.veko.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import com.veko.domain.model.Weather

@Entity(tableName = "city_table", primaryKeys = ["lon", "lat"])
data class WeatherEntity(
    @ColumnInfo(name = "lon")
    val lon: Double,
    @ColumnInfo(name = "lat")
    val lat: Double,
    @ColumnInfo(name = "time_zone_offset")
    val timeZoneOffset: Long,
    @Embedded
    val latest: CurrentWeatherEntity,
    @ColumnInfo(name = "daily")
    val daily: List<DailyWeatherEntity>
)

fun WeatherEntity.toDomainModel() = Weather(
    city = latest.city,
    timeZoneOffset = timeZoneOffset,
    currentWeather = latest.toDomainModel(),
    dailyWeather = daily.map(DailyWeatherEntity::toDomainModel)
)