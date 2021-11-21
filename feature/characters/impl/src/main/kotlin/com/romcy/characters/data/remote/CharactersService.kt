package com.romcy.characters.data.remote

import com.romcy.characters.data.response.CharactersResult
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CharactersService @Inject constructor(
    private val charactersApi: CharactersApi
) : CharactersServiceApi {

    override suspend fun getCharacters(): CharactersResult {
        return charactersApi.getCharacters()
    }
}