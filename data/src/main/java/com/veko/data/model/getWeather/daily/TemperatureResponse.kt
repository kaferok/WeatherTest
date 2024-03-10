package com.veko.data.model.getWeather.daily

import com.google.gson.annotations.SerializedName
import com.veko.data.storage.entity.TemperatureEntity

data class TemperatureResponse(
    @SerializedName("morn")
    val morning: Double,
    @SerializedName("day")
    val day: Double,
    @SerializedName("eve")
    val evening: Double,
    @SerializedName("night")
    val night: Double
)

fun TemperatureResponse.toEntityModel() = TemperatureEntity(
    morning = morning,
    day = day,
    evening = evening,
    night = night
)