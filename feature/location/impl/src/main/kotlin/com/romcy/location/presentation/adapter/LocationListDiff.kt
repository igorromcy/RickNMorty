package com.romcy.location.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.romcy.location.domain.model.LocationUIModel

object LocationListDiff : DiffUtil.ItemCallback<LocationUIModel>() {
    override fun areItemsTheSame(
        oldItem: LocationUIModel,
        newItem: LocationUIModel
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: LocationUIModel,
        newItem: LocationUIModel
    ) = oldItem == newItem
}