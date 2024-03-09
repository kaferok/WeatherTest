package com.veko.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veko.domain.model.WeatherDescription

@Entity(tableName = "weather_description_table")
data class CommonWeatherEntity(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "main")
    val main: String,
    @ColumnInfo(name = "icon")
    val icon: String
)

fun CommonWeatherEntity.toDomainModel() = WeatherDescription(
    id = id,
    description = description,
    main = main,
    icon = icon
)