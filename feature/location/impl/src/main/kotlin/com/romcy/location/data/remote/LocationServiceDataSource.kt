package com.romcy.location.data.remote

import com.romcy.core.ricknmorty.utility.Result
import com.romcy.core.ricknmorty.utility.safeApiCall
import com.romcy.location.domain.model.ContentErrorModel
import com.romcy.location.domain.model.LocationContentModel
import com.romcy.location.domain.model.NetworkError
import javax.inject.Inject

class LocationServiceDataSource @Inject constructor(
    private val locationServiceApi: LocationServiceApi
) : LocationRemoteDataSource {

    override suspend fun getLocations(): Result<LocationContentModel, ContentErrorModel> {
        return safeApiCall {
            locationServiceApi.getLocations()
        }.mapSuccess {
            return@mapSuccess LocationContentModel(
                  locations = it.results
            )
        }.mapError {
            return@mapError NetworkError(it)
        }
    }
}