package com.romcy.characters.domain.model

import com.romcy.characters.data.response.CharactersResponse

data class CharactersContentModel(
    val characters: List<CharactersResponse>,
)

fun CharactersResponse.toCharactersUIModel() = CharactersUIModel(
    id = id,
    name = name,
    status = status,
    species = species,
    type = type,
    gender = gender,
    image = image
)