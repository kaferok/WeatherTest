package com.veko.data.model.getWeather

import com.google.gson.annotations.SerializedName
import com.veko.data.model.getWeather.detail.CommonWeather
import com.veko.data.model.getWeather.detail.toEntityModel
import com.veko.data.storage.entity.CurrentWeatherEntity

data class CurrentWeatherResponse(
    @SerializedName("dt")
    val dt: Long,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("clouds")
    val clouds: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("wind_speed")
    val windSpeed: Double,
    @SerializedName("weather")
    val weather: List<CommonWeather>
)

fun CurrentWeatherResponse.toEntityModel(city: String) = CurrentWeatherEntity(
    date = dt,
    city = city,
    temperature = temp,
    feelsLike = feelsLike,
    pressure = pressure,
    humidity = humidity,
    visibility = visibility,
    windSpeed = windSpeed,
    weatherDescription = weather.firstOrNull()?.toEntityModel()
)