package com.romcy.location.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romcy.location.domain.model.ContentErrorModel
import com.romcy.location.domain.model.LocationContentModel
import com.romcy.location.domain.model.LocationUIModel
import com.romcy.location.domain.model.LocationViewAction
import com.romcy.location.domain.usecase.GetLocationContent
import com.romcy.core.ricknmorty.base.BaseViewModel
import com.romcy.location.data.response.LocationResponse
import com.romcy.core.ricknmorty.dependencies.module.ViewModelKey
import com.romcy.scopes.ViewModelScope
import com.squareup.anvil.annotations.ContributesMultibinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@ContributesMultibinding(ViewModelScope::class, ViewModel::class)
@ViewModelKey(LocationViewModel::class)
class LocationViewModel @Inject constructor(
    override val viewState: LocationViewState,
    private val getLocationContent: GetLocationContent,
): BaseViewModel<LocationViewState, LocationViewAction>() {

    init {
        viewModelScope.launch {
            fetchContents()
        }
    }

    private suspend fun fetchContents() {
        viewState.pageState.value = LocationPageState.ContentRefreshing
        getLocationContent()
            .onSuccess {
                onContentSuccess(it)
            }
            .onError {
                onContentError(it)
            }
    }

    private fun onContentSuccess(locationContentModel: LocationContentModel) {
        viewState.locations.value = toLocationUIModel(locationContentModel.locations)
        viewState.pageState.value = LocationPageState.Content
    }

    private fun onContentError(contentErrorModel: ContentErrorModel) {

    }

    override fun dispatchViewAction(viewAction: LocationViewAction) {
        when (viewAction) {
            LocationViewAction.ViewLocations -> handleViewLocationAction()
        }
    }

    private fun handleViewLocationAction() {

    }
}

private fun toLocationUIModel(locationResponse: List<LocationResponse>):
        List<LocationUIModel> = locationResponse.map {
    LocationUIModel(
        id = it.id,
        name = it.name,
        type = it.type,
        dimension = it.dimension,
        created = ""
    )
}