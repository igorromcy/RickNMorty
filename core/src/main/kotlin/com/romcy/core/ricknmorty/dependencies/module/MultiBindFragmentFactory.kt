package com.romcy.core.ricknmorty.dependencies.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesBinding
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@ContributesBinding(AppScope::class, FragmentFactory::class)
class MultiBindFragmentFactory @Inject constructor(
    private val map: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        return map[fragmentClass]?.get() ?: super.instantiate(classLoader, className)
    }
}

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.CLASS
)
@Retention(value = AnnotationRetention.RUNTIME)
@MapKey
annotation class FragmentKey(val value: KClass<out Fragment>)
