package com.romcy.location.data.remote

import com.romcy.core.ricknmorty.utility.Result
import com.romcy.location.domain.LocationRepository
import com.romcy.location.domain.model.ContentErrorModel
import com.romcy.location.domain.model.LocationContentModel
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class LocationDefaultRepository @Inject constructor(
    private val serviceDataSource: LocationServiceDataSource
) : LocationRepository{
    override suspend fun getLocations(): Result<LocationContentModel, ContentErrorModel> {
        return serviceDataSource.getLocations()
    }

}