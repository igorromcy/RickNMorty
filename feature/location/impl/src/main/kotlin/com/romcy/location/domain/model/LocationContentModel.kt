package com.romcy.location.domain.model

import com.romcy.location.data.response.LocationResponse

data class LocationContentModel(
    val locations: List<LocationResponse>,
)

fun LocationResponse.toLocationUIModel() = LocationUIModel(
    id = id,
    name = name,
    type = type,
    dimension = dimension,
    created = ""
)