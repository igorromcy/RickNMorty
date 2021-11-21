package com.romcy.location.domain.usecase

import com.romcy.location.domain.LocationRepository
import com.romcy.location.domain.model.ContentErrorModel
import com.romcy.location.domain.model.LocationContentModel
import com.romcy.core.ricknmorty.utility.Result
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class GetLocationContent @Inject constructor(
    private val locationRepository: LocationRepository,
) : GetLocationContentUseCase {
    override suspend fun invoke(
    ): Result<LocationContentModel, ContentErrorModel> {
        return locationRepository.getLocations()
    }
}
