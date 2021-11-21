package com.romcy.characters.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.romcy.core.ricknmorty.base.BaseViewModel
import com.romcy.core.ricknmorty.dependencies.module.ViewModelKey
import com.romcy.characters.data.response.CharactersResponse
import com.romcy.characters.domain.model.CharactersContentModel
import com.romcy.characters.domain.model.ContentErrorModel
import com.romcy.characters.domain.model.CharactersUIModel
import com.romcy.characters.domain.usecase.GetCharactersContent
import com.romcy.scopes.ViewModelScope
import com.squareup.anvil.annotations.ContributesMultibinding
import kotlinx.coroutines.launch
import javax.inject.Inject

@ContributesMultibinding(ViewModelScope::class, ViewModel::class)
@ViewModelKey(CharactersViewModel::class)
class CharactersViewModel @Inject constructor(
    override val viewState: CharactersViewState,
    private val getCharactersContent: GetCharactersContent,
) : BaseViewModel<CharactersViewState, CharactersViewAction>() {

    init {
        viewModelScope.launch {
            fetchContents()
        }
    }

    private suspend fun fetchContents() {
        viewState.pageState.value = CharactersPageState.ContentRefreshing
        getCharactersContent()
            .onSuccess {
                onContentSuccess(it)
            }
            .onError {
                onContentError(it)
            }
    }

    private fun onContentSuccess(charactersContentModel: CharactersContentModel) {
        viewState.characters.value = toCharactersUIModel(charactersContentModel.characters)
        viewState.pageState.value = CharactersPageState.Content
    }

    private fun onContentError(contentErrorModel: ContentErrorModel) {

    }

    override fun dispatchViewAction(viewAction: CharactersViewAction) {
        when (viewAction) {
            is CharactersViewAction.ViewCharacters -> handleViewCharactersAction()
        }
    }

    private fun handleViewCharactersAction() {

    }
}

private fun toCharactersUIModel(charactersResponse: List<CharactersResponse>):
        List<CharactersUIModel> = charactersResponse.map {
    CharactersUIModel(
        id = it.id,
        name = it.name,
        status = it.status,
        species = it.species,
        type = it.type,
        gender = it.gender,
        image = it.image
    )
}