package com.veko.domain.model

data class WeatherDescription(
    val id: Int,
    val description: String,
    val main: String,
    val icon: String
)