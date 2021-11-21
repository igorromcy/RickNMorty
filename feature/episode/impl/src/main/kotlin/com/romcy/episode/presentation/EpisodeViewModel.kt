package com.romcy.episode.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romcy.core.ricknmorty.dependencies.module.ViewModelKey
import com.romcy.scopes.ViewModelScope
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

@ContributesMultibinding(ViewModelScope::class, ViewModel::class)
@ViewModelKey(EpisodeViewModel::class)
class EpisodeViewModel @Inject constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}