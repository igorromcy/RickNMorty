package com.romcy.characters.domain.usecase

import com.romcy.characters.domain.model.CharactersContentModel
import com.romcy.characters.domain.model.ContentErrorModel
import com.romcy.core.ricknmorty.utility.Result

interface GetCharactersContentUseCase {
    suspend operator fun invoke():
            Result<CharactersContentModel, ContentErrorModel>
}
