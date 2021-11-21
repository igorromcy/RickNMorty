package com.romcy.location.data.remote

import com.romcy.location.data.response.LocationResult
import retrofit2.http.GET

interface LocationApi {

    @GET("location")
    suspend fun getLocation(): LocationResult
}