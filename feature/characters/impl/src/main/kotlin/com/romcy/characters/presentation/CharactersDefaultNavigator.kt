package com.romcy.characters.presentation

import com.romcy.characters.CharactersNavigator
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject
import javax.inject.Provider

@ContributesBinding(AppScope::class)
class CharactersDefaultNavigator @Inject constructor(
    private val charactersFragmentProvider: Provider<CharactersFragment>
) : CharactersNavigator {

    override fun getCharactersFragment(): CharactersFragment = charactersFragmentProvider.get()
}
