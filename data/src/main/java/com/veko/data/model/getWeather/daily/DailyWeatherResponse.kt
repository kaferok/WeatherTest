package com.veko.data.model.getWeather.daily

import com.google.gson.annotations.SerializedName
import com.veko.data.model.getWeather.detail.CommonWeather
import com.veko.data.model.getWeather.detail.toEntityModel
import com.veko.data.storage.entity.DailyWeatherEntity

data class DailyWeatherResponse(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("temp")
    val temp: TemperatureResponse,
    @SerializedName("feels_like")
    val feelsLike: TemperatureResponse,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("weather")
    val weather: List<CommonWeather>
)

fun DailyWeatherResponse.toEntityModel() = DailyWeatherEntity(
    date = dt,
    temperature = temp.toEntityModel(),
    feelsLike = feelsLike.toEntityModel(),
    pressure = pressure,
    humidity = humidity,
    windSpeed = windSpeed,
    weatherDescription = weather.firstOrNull()?.toEntityModel()
)