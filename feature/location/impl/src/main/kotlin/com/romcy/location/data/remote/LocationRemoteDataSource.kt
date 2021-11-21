package com.romcy.location.data.remote

import com.romcy.core.ricknmorty.utility.Result
import com.romcy.location.domain.model.ContentErrorModel
import com.romcy.location.domain.model.LocationContentModel

interface LocationRemoteDataSource {
    suspend fun getLocations(): Result<LocationContentModel, ContentErrorModel>
}