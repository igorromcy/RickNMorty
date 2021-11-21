package com.romcy.characters.domain.model

import com.romcy.core.ricknmorty.utility.ResultError

sealed class ContentErrorModel

data class NetworkError(val error: ResultError) : ContentErrorModel()