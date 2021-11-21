package com.romcy.characters.data.remote

import com.romcy.characters.domain.model.*
import com.romcy.core.ricknmorty.utility.Result
import com.romcy.core.ricknmorty.utility.safeApiCall
import javax.inject.Inject

class CharactersServiceDataSource @Inject constructor(
    private val charactersServiceApi: CharactersServiceApi
) : CharactersRemoteDataSource {

    override suspend fun getCharacters(): Result<CharactersContentModel, ContentErrorModel> {
        return safeApiCall {
            charactersServiceApi.getCharacters()
        }.mapSuccess {
            return@mapSuccess CharactersContentModel(
                characters = it.results
            )
        }.mapError {
            return@mapError NetworkError(it)
        }
    }
}