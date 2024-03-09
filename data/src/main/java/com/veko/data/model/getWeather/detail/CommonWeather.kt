package com.veko.data.model.getWeather.detail

import com.squareup.moshi.Json
import com.veko.data.storage.entity.CommonWeatherEntity

data class CommonWeather(
    @Json(name = "id")
    val id: Int,
    @Json(name = "main")
    val main: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "icon")
    val icon: String
)

fun CommonWeather.toEntityModel() = CommonWeatherEntity(
    id = id,
    description = description,
    main = main,
    icon = icon
)