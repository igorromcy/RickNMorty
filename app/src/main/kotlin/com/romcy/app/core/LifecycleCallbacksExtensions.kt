package com.romcy.app.core

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

fun Application.registerActivityCreateCallback(callback: (Activity) -> Unit) {
    registerActivityLifecycleCallbacks(
        object : EmptyActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                callback.invoke(activity)
            }
        }
    )
}

fun FragmentActivity.registerFragmentPreCreateCallback(
    onPreCreate: (Fragment) -> Unit
) {
    supportFragmentManager.registerFragmentLifecycleCallbacks(
        object : FragmentManager.FragmentLifecycleCallbacks() {
            override fun onFragmentPreCreated(
                fragmentManager: FragmentManager,
                fragment: Fragment,
                savedInstanceState: Bundle?
            ) {
                super.onFragmentCreated(fragmentManager, fragment, savedInstanceState)
                onPreCreate(fragment)
            }
        },
        true
    )
}
