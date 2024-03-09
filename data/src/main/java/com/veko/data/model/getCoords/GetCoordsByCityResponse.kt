package com.veko.data.model.getCoords

import com.squareup.moshi.Json

data class GetCoordsByCityResponse(
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double,
    @Json(name = "name")
    val name: String
)