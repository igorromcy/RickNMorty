@file:Suppress("unused")

package com.romcy.core.ricknmorty.base

import android.content.Context
import androidx.databinding.ViewDataBinding

fun ViewDataBinding.context(): Context = root.context

val ViewDataBinding.context: Context
    get() = root.context

val <T> T.exhaustive: T
    get() = this
