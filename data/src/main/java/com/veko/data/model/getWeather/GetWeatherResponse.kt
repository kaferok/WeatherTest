package com.veko.data.model.getWeather

import com.google.gson.annotations.SerializedName
import com.veko.data.model.getWeather.daily.DailyWeatherResponse
import com.veko.data.model.getWeather.daily.toEntityModel
import com.veko.data.storage.entity.WeatherEntity


data class GetWeatherResponse(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("timezone_offset")
    val timeZoneOffset: Long,
    @SerializedName("current")
    val current: CurrentWeatherResponse,
    @SerializedName("daily")
    val daily: List<DailyWeatherResponse>
)

fun GetWeatherResponse.toEntityModel(city: String) = WeatherEntity(
    timeZoneOffset = timeZoneOffset,
    lon = lon,
    lat = lat,
    latest = current.toEntityModel(city),
    daily = daily.map(DailyWeatherResponse::toEntityModel)
)