package com.romcy.characters.data.remote

import com.romcy.characters.domain.model.CharactersContentModel
import com.romcy.characters.domain.model.ContentErrorModel
import com.romcy.core.ricknmorty.utility.Result

interface CharactersRemoteDataSource {
    suspend fun getCharacters(): Result<CharactersContentModel, ContentErrorModel>
}