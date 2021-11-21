package com.romcy.location.domain.usecase

import com.romcy.location.domain.model.ContentErrorModel
import com.romcy.location.domain.model.LocationContentModel
import com.romcy.core.ricknmorty.utility.Result

interface GetLocationContentUseCase {
    suspend operator fun invoke():
            Result<LocationContentModel, ContentErrorModel>
}
