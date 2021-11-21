package com.romcy.episode.di

import androidx.lifecycle.ViewModel
import com.romcy.core.ricknmorty.dependencies.module.ViewModelKey
import com.romcy.episode.presentation.EpisodeFragment
import com.romcy.episode.presentation.EpisodeViewModel
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
@ContributesTo(AppScope::class)
interface EpisodeModule {

    @ContributesAndroidInjector
    fun contributeEpisodeFragment(): EpisodeFragment

    @[Binds IntoMap ViewModelKey(EpisodeViewModel::class)]
    fun bindsEpisodeViewModel(episodeViewModel: EpisodeViewModel): ViewModel

}