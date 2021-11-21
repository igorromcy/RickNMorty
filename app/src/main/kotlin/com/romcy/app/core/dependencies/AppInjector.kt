package com.romcy.app.core.dependencies

import com.romcy.app.core.CustomApplication
import com.romcy.core.ricknmorty.dependencies.Injectable
import com.romcy.app.core.dependencies.component.ApplicationComponent
import com.romcy.app.core.dependencies.component.DaggerApplicationComponent
import com.romcy.app.core.registerActivityCreateCallback
import com.romcy.app.core.registerFragmentPreCreateCallback
import dagger.android.AndroidInjection
import dagger.android.support.AndroidSupportInjection

object AppInjector {

    fun init(customApplication: CustomApplication): ApplicationComponent {
        val appComponent = DaggerApplicationComponent.builder().application(customApplication).build()
        appComponent.inject(customApplication)
        customApplication.registerActivityCreateCallback { activity ->
            if (activity is Injectable) {
                AndroidInjection.inject(activity)
            }
            if (activity is androidx.fragment.app.FragmentActivity) {
                activity.registerFragmentPreCreateCallback { fragment ->
                    if (fragment is Injectable) {
                        AndroidSupportInjection.inject(fragment)
                    }
                }
            }
        }

        return appComponent
    }
}
