package com.romcy.characters.di

import androidx.lifecycle.ViewModel
import com.romcy.characters.CharactersNavigator
import com.romcy.characters.presentation.CharactersDefaultNavigator
import com.romcy.core.ricknmorty.dependencies.module.ViewModelKey
import com.romcy.characters.presentation.CharactersFragment
import com.romcy.characters.presentation.CharactersViewModel
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@ContributesTo(AppScope::class)
interface CharactersModule {

    @ContributesAndroidInjector
    fun contributeCharactersFragment(): CharactersFragment

    @[Binds IntoMap ViewModelKey(CharactersViewModel::class)]
    fun bindsCharactersViewModel(charactersViewModel: CharactersViewModel): ViewModel

//    @[Binds Reusable]
//    fun bindsCharactersDefaultNavigator(charactersDefaultNavigator: CharactersDefaultNavigator): CharactersNavigator

}