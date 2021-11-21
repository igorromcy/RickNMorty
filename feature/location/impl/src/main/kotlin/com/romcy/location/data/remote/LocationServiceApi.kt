package com.romcy.location.data.remote

import com.romcy.location.data.response.LocationResult

interface LocationServiceApi {

    suspend fun getLocations(): LocationResult
}