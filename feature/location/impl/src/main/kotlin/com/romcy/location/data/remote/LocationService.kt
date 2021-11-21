package com.romcy.location.data.remote

import com.romcy.location.data.response.LocationResult
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class LocationService @Inject constructor(
    private val locationApi: LocationApi
) : LocationServiceApi {

    override suspend fun getLocations(): LocationResult {
        return locationApi.getLocation()
    }
}