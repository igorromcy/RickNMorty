package com.romcy.characters.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.romcy.characters.domain.model.CharactersUIModel

object CharactersListDiff : DiffUtil.ItemCallback<CharactersUIModel>() {
    override fun areItemsTheSame(
        oldItem: CharactersUIModel,
        newItem: CharactersUIModel
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: CharactersUIModel,
        newItem: CharactersUIModel
    ) = oldItem == newItem
}