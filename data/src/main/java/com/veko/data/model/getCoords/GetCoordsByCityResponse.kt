package com.veko.data.model.getCoords

import com.google.gson.annotations.SerializedName

data class GetCoordsByCityResponse(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double,
    @SerializedName("name")
    val name: String
)