package com.romcy.location.domain

import com.romcy.location.domain.model.ContentErrorModel
import com.romcy.location.domain.model.LocationContentModel
import com.romcy.core.ricknmorty.utility.Result

interface LocationRepository {

    suspend fun getLocations(): Result<LocationContentModel, ContentErrorModel>
}