package com.romcy.location.data.response

import com.google.gson.annotations.SerializedName

data class LocationResult(
    @SerializedName("info")
    val infoResponse: InfoResponse,
    @SerializedName("results")
    val results: List<LocationResponse>
)

data class InfoResponse(
    @SerializedName("count")
    val count: Int
)

data class LocationResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("dimension")
    val dimension: String,
    @SerializedName("type")
    val type: String,
)
