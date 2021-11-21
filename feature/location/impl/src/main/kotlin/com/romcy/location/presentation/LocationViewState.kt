package com.romcy.location.presentation

import androidx.lifecycle.MutableLiveData
import com.romcy.location.domain.model.LocationUIModel
import javax.inject.Inject

class LocationViewState @Inject constructor() {
    val pageState: MutableLiveData<LocationPageState> = MutableLiveData(LocationPageState.Init)
    val locations: MutableLiveData<List<LocationUIModel>> = MutableLiveData()
}

sealed class LocationPageState {
    object Init : LocationPageState()
    object Content : LocationPageState()
    object ContentRefreshing : LocationPageState()
    object Empty : LocationPageState()
}