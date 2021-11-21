package com.romcy.characters.presentation

import androidx.lifecycle.MutableLiveData
import com.romcy.characters.domain.model.CharactersUIModel
import javax.inject.Inject

class CharactersViewState @Inject constructor() {
    val pageState: MutableLiveData<CharactersPageState> = MutableLiveData(CharactersPageState.Init)
    val characters: MutableLiveData<List<CharactersUIModel>> = MutableLiveData()
}

sealed class CharactersPageState {
    object Init : CharactersPageState()
    object Content : CharactersPageState()
    object ContentRefreshing : CharactersPageState()
    object Empty : CharactersPageState()
}