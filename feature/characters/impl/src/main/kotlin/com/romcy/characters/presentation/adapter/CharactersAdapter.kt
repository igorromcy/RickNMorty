package com.romcy.characters.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.romcy.characters.domain.model.CharactersUIModel
import com.romcy.characters.presentation.CharactersViewModel
import com.romcy.characters.presentation.viewholder.CharactersListItemViewHolder

class CharactersAdapter constructor(
    private val viewModel: CharactersViewModel,
) : ListAdapter<CharactersUIModel, CharactersListItemViewHolder>(CharactersListDiff){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CharactersListItemViewHolder(parent)

    override fun onBindViewHolder(
        holder: CharactersListItemViewHolder,
        position: Int
    ) {
       holder.bind(
           item = getItem(position),
           position = position,
           viewModel = viewModel
       )
    }
}