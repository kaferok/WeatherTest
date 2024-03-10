package com.veko.data.storage.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.veko.data.storage.entity.DailyWeatherEntity
import com.veko.data.storage.utils.fromJson

class DailyWeatherConverter {

    private val gson = Gson()

    @TypeConverter
    fun fromList(value: List<DailyWeatherEntity>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<DailyWeatherEntity> {
        return gson.fromJson<List<DailyWeatherEntity>>(value).orEmpty()
    }
}