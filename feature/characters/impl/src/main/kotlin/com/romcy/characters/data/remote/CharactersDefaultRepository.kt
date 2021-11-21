package com.romcy.characters.data.remote

import com.romcy.characters.domain.CharactersRepository
import com.romcy.characters.domain.model.CharactersContentModel
import com.romcy.characters.domain.model.ContentErrorModel
import com.romcy.core.ricknmorty.utility.Result
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class CharactersDefaultRepository @Inject constructor(
    private val serviceDataSource: CharactersServiceDataSource
) : CharactersRepository{

    override suspend fun getCharacters(): Result<CharactersContentModel, ContentErrorModel> {
        return serviceDataSource.getCharacters()
    }
}