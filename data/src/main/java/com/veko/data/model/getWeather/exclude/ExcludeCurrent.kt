package com.veko.data.model.getWeather.exclude

import com.squareup.moshi.Json
import com.veko.data.model.getWeather.detail.CommonWeather
import com.veko.data.model.getWeather.detail.toEntityModel
import com.veko.data.storage.entity.LatestWeatherEntity

data class ExcludeCurrent(
    @Json(name = "dt")
    val dt: Long,
    @Json(name = "sunrise")
    val sunrise: Long,
    @Json(name = "sunset")
    val sunset: Long,
    @Json(name = "temp")
    val temp: Double,
    @Json(name = "feels_like")
    val feelsLike: Double,
    @Json(name = "pressure")
    val pressure: Int,
    @Json(name = "humidity")
    val humidity: Int,
    @Json(name = "clouds")
    val clouds: Int,
    @Json(name = "visibility")
    val visibility: Int,
    @Json(name = "wind_speed")
    val windSpeed: Double,
    @Json(name = "weather")
    val weather: List<CommonWeather>
)

fun ExcludeCurrent.toEntityModel(city: String) = LatestWeatherEntity(
    city = city,
    temperature = temp,
    feelsLike = feelsLike,
    pressure = pressure,
    humidity = humidity,
    visibility = visibility,
    windSpeed = windSpeed,
    weatherDescription = weather.firstOrNull()?.toEntityModel()
)