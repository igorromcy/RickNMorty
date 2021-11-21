package com.romcy.characters.data.response

import com.google.gson.annotations.SerializedName

data class CharactersResult(
    @SerializedName("info")
    val infoResponse: InfoResponse,
    @SerializedName("results")
    val results: List<CharactersResponse>
)

data class InfoResponse(
    @SerializedName("count")
    val count: Int
)

data class CharactersResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String,
)
