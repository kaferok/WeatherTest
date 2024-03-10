package com.veko.data.model.getWeather

import com.google.gson.annotations.SerializedName
import com.veko.data.model.getWeather.exclude.ExcludeCurrent
import com.veko.data.model.getWeather.exclude.toEntityModel
import com.veko.data.storage.entity.CityEntity

data class GetWeatherResponse(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("timeZone")
    val tineZone: String,
    @SerializedName("current")
    val current: ExcludeCurrent
)

fun GetWeatherResponse.toEntityModel(city: String) = CityEntity(
    lon = lon,
    lat = lat,
    latestWeather = current.toEntityModel(city)
)