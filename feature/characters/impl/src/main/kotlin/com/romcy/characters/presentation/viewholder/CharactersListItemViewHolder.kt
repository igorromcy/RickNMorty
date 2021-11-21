package com.romcy.characters.presentation.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.romcy.characters.domain.model.CharactersUIModel
import com.romcy.characters.impl.databinding.CharactersListBinding
import com.romcy.characters.presentation.CharactersViewModel
import com.romcy.core.ricknmorty.base.context

class CharactersListItemViewHolder private constructor(
    private val binding: CharactersListBinding,
) : RecyclerView.ViewHolder(binding.root) {

    constructor(
        parentView: ViewGroup,
    ) : this(
        CharactersListBinding.inflate(
            LayoutInflater.from(parentView.context),
            parentView,
            false
        )
    )

    fun bind(
        item: CharactersUIModel,
        position: Int,
        viewModel: CharactersViewModel,
    ) {
        binding.name.text = item.name
        binding.status.text = item.status
        binding.specie.text = item.species
        binding.gender.text = item.gender

        Glide.with(binding.context)
            .load(item.image)
            .into(binding.image);
    }
}
