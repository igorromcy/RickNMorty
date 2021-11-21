package com.romcy.app.core

import android.app.Application
import com.romcy.app.core.dependencies.AppInjector
import com.romcy.app.core.dependencies.component.ApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CustomApplication : Application(), HasAndroidInjector {

    private lateinit var appComponent: ApplicationComponent

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        appComponent = getAppInjector()
    }

    private fun getAppInjector() = AppInjector.init(this)

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}