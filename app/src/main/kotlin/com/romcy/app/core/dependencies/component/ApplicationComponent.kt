package com.romcy.app.core.dependencies.component

import android.app.Application
import com.romcy.app.core.CustomApplication
import com.romcy.scopes.AppScope
import com.romcy.scopes.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@SingleIn(AppScope::class)
@MergeComponent(
    scope = AppScope::class,
    modules = [
        AndroidInjectionModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<CustomApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(customApplication: CustomApplication)
}

