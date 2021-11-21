package com.romcy.location.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.romcy.location.domain.model.LocationUIModel
import com.romcy.location.presentation.LocationViewModel
import com.romcy.location.presentation.viewholder.LocationListItemViewHolder

class LocationAdapter constructor(
    private val viewModel: LocationViewModel,
) : ListAdapter<LocationUIModel, LocationListItemViewHolder>(LocationListDiff){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = LocationListItemViewHolder(parent)

    override fun onBindViewHolder(
        holder: LocationListItemViewHolder,
        position: Int
    ) {
        holder.bind(
            item = getItem(position),
            position = position,
            viewModel = viewModel
        )
    }
}