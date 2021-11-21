package com.romcy.characters.data.remote

import com.romcy.characters.data.response.CharactersResult

interface CharactersServiceApi {

    suspend fun getCharacters(): CharactersResult
}