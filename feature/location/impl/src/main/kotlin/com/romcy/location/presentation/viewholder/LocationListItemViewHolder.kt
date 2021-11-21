package com.romcy.location.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.romcy.location.databinding.LocationListBinding
import com.romcy.location.domain.model.LocationUIModel
import com.romcy.location.presentation.LocationViewModel

class LocationListItemViewHolder private constructor(
    private val binding: LocationListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parentView: ViewGroup,
    ) : this(
        LocationListBinding.inflate(
            LayoutInflater.from(parentView.context),
            parentView,
            false
        )
    )

    fun bind(
        item: LocationUIModel,
        position: Int,
        viewModel: LocationViewModel,
    ) {
        binding.name.text = item.name
    }
}