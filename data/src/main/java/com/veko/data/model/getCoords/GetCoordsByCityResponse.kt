package com.veko.data.model.getCoords

import com.google.gson.annotations.SerializedName

data class GetCoordsByCityResponse(
    val lat: Double,
    val lon: Double,
    val name: String,
    @SerializedName("local_names")
    val locale: LocaleReponse
)