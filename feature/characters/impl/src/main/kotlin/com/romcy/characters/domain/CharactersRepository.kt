package com.romcy.characters.domain

import com.romcy.characters.domain.model.CharactersContentModel
import com.romcy.characters.domain.model.ContentErrorModel
import com.romcy.core.ricknmorty.utility.Result

interface CharactersRepository {

    suspend fun getCharacters(): Result<CharactersContentModel, ContentErrorModel>
}