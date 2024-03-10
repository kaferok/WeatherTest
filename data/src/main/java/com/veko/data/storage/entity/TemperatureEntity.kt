package com.veko.data.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.veko.domain.model.Temperature

@Entity(tableName = "temperature_table")
data class TemperatureEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "temperature_id")
    val id: Int = 0,
    @ColumnInfo(name = "morn")
    val morning: Double,
    @ColumnInfo(name = "day")
    val day: Double,
    @ColumnInfo(name = "eve")
    val evening: Double,
    @ColumnInfo(name = "night")
    val night: Double
)

fun TemperatureEntity.toDomainModel() = Temperature(
    morning = morning, day = day, evening = evening, night = night
)