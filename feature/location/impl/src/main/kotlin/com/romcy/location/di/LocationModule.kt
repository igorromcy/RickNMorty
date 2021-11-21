package com.romcy.location.di

import androidx.lifecycle.ViewModel
import com.romcy.core.ricknmorty.dependencies.module.ViewModelKey
import com.romcy.location.data.remote.LocationApi
import com.romcy.location.presentation.LocationFragment
import com.romcy.location.presentation.LocationViewModel
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
@ContributesTo(AppScope::class)
interface LocationModule {

    @ContributesAndroidInjector
    fun contributeLocationFragment(): LocationFragment

    @[Binds IntoMap ViewModelKey(LocationViewModel::class)]
    fun bindsLocationViewModel(locationViewModel: LocationViewModel): ViewModel
}