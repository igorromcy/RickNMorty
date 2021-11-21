package com.romcy.characters.domain.usecase

import com.romcy.characters.domain.CharactersRepository
import com.romcy.characters.domain.model.CharactersContentModel
import com.romcy.characters.domain.model.ContentErrorModel
import com.romcy.core.ricknmorty.utility.Result
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class GetCharactersContent @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : GetCharactersContentUseCase {
    override suspend fun invoke(
    ): Result<CharactersContentModel, ContentErrorModel> {
        return charactersRepository.getCharacters()
    }
}
