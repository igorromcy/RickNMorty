package com.romcy.characters.domain.model

data class CharactersUIModel(
    val id: Long,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
//    val origin: OriginResponse,
//    val location: LocationResponse,
    val image: String,
//    val episode: List<String>,
//    val url: String,
//    val created: String
)
