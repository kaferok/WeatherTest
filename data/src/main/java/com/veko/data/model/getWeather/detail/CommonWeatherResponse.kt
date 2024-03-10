package com.veko.data.model.getWeather.detail

import com.google.gson.annotations.SerializedName
import com.veko.data.storage.entity.CommonWeatherEntity

private const val ICON_PREFIX = "https://openweathermap.org/img/wn/"
private const val ICON_SUFFIX = ".png"

data class CommonWeather(
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)

fun CommonWeather.toEntityModel() = CommonWeatherEntity(
    id = id,
    description = description,
    main = main,
    icon = ICON_PREFIX + icon + ICON_SUFFIX
)