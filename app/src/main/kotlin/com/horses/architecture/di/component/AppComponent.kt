package com.horses.architecture.di.component

import android.content.Context
import com.horses.architecture.data.retrofit.ApiService
import com.horses.architecture.di.module.AppModule
import com.horses.architecture.di.module.NetworkModule
import com.horses.architecture.di.module.UsesCaseModule
import com.horses.architecture.domain.usecase.GetPokemons
import dagger.Component
import javax.inject.Singleton

/**
 * @author by GERARDO on 6/04/2018.
 */
@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, UsesCaseModule::class])
interface AppComponent {

    fun context(): Context

    fun apiService(): ApiService

    fun getPokemonsUseCase(): GetPokemons
}