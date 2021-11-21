package com.romcy.characters.data.remote

import com.romcy.characters.data.response.CharactersResult
import retrofit2.http.GET

interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(): CharactersResult
}