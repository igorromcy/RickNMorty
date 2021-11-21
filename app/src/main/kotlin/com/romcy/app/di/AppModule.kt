package com.romcy.app.di

import androidx.lifecycle.ViewModelProvider
import com.romcy.app.BuildConfig
import com.romcy.app.MainActivity
import com.romcy.core.ricknmorty.dependencies.module.ViewModelProviderFactory
import com.romcy.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.android.ContributesAndroidInjector
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@ContributesTo(AppScope::class)
interface AppModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @[Binds Reusable]
    fun bindsViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    companion object {

        @[Provides Reusable]
        fun provideInterceptors(): ArrayList<Interceptor> {
            val interceptors = arrayListOf<Interceptor>()
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            }
            interceptors.add(loggingInterceptor)
            return interceptors
        }

        @[JvmStatic Provides]
        fun provideOkHttpClient(
            interceptors: ArrayList<Interceptor>
        ): OkHttpClient {
            val clientBuilder = OkHttpClient.Builder()
                .followRedirects(false)
            interceptors.forEach {
                clientBuilder.addInterceptor(it)
            }
            return clientBuilder.build()
        }

        @[JvmStatic Provides]
        fun provideRetrofit(
            okHttpClient: OkHttpClient,
        ): Retrofit {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rickandmortyapi.com/api/")
                .client(okHttpClient)
                .build()
        }

    }

}