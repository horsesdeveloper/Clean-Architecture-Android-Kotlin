package com.horses.architecture.di.module

import com.horses.architecture.data.repository.JedisRepository
import com.horses.architecture.data.repository.PokemonsRepository
import com.horses.architecture.data.retrofit.ApiService
import com.horses.architecture.domain.usecase.GetJedis
import com.horses.architecture.domain.usecase.GetPokemons
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

@Module
class UsesCaseModule {

    @Provides
    @Singleton
    fun getPokemonsRepository(apiService: ApiService) = PokemonsRepository(apiService)

    @Provides
    @Singleton
    fun getJedisRepository() = JedisRepository()

    @Provides
    @Singleton
    fun getPokemons(
            repository: PokemonsRepository,
            @Named("executor_thread") executorThread: Scheduler,
            @Named("ui_thread") uiThread: Scheduler
    ) = GetPokemons(executorThread, uiThread, repository)

    @Provides
    @Singleton
    fun getJedis(
            repository: JedisRepository,
            @Named("executor_thread") executorThread: Scheduler,
            @Named("ui_thread") uiThread: Scheduler
    ) = GetJedis(executorThread, uiThread, repository)

    @Provides
    @Named("executor_thread")
    fun provideExecutorThread(): Scheduler = Schedulers.io()

    @Provides
    @Named("ui_thread")
    fun provideUiThread(): Scheduler = AndroidSchedulers.mainThread()
}