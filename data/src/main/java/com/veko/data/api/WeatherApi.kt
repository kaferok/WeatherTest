package com.veko.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {

    @GET("data/3.0/onecall")
    suspend fun getWeather(@Path("city") city:String): Result<Any>
}