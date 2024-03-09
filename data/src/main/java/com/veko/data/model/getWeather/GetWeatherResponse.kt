package com.veko.data.model.getWeather

import com.squareup.moshi.Json
import com.veko.data.model.getWeather.exclude.ExcludeCurrent
import com.veko.data.model.getWeather.exclude.toEntityModel
import com.veko.data.storage.entity.CityEntity

data class GetWeatherResponse(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "timeZone")
    val tineZone: String,
    @Json(name = "current")
    val current: ExcludeCurrent
)

fun GetWeatherResponse.toEntityModel(city: String) = CityEntity(
    lon = lon,
    lat = lat,
    latestWeather = current.toEntityModel(city)
)