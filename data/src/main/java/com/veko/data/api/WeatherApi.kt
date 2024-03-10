package com.veko.data.api

import com.veko.data.model.getCoords.GetCoordsByCityResponse
import com.veko.data.model.getWeather.GetWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/3.0/onecall")
    suspend fun getCoordsByCity(
        @Query("q") city: String
    ): Result<List<GetCoordsByCityResponse>>

    @GET("data/3.0/onecall")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("lang") lang: String = "ru",
        @Query("units") unit: String ="metric"
    ): Result<GetWeatherResponse>
}