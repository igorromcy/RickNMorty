package com.romcy.episode.presentation

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.romcy.core.ricknmorty.dependencies.Injectable
import com.romcy.core.ricknmorty.dependencies.module.FragmentKey
import com.romcy.episode.R
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

@ContributesMultibinding(AppScope::class, Fragment::class)
@FragmentKey(EpisodeFragment::class)
class EpisodeFragment @Inject constructor(
    private val viewModelProviderFactory: ViewModelProvider.Factory,
) : Fragment(R.layout.episode_fragment), Injectable {

    private lateinit var episodeViewModel: EpisodeViewModel

}