package com.horses.architecture.di.module

import android.content.Context
import com.horses.architecture.di.PresenterScope
import com.horses.architecture.domain.usecase.GetPokemons
import com.horses.architecture.view.presenter.PokemonPresenter
import dagger.Module
import dagger.Provides
/**
 * @author @briansalvattore on 07/03/2018.
 */
@Module
@PresenterScope
class PresenterModule {

    @Provides
    @PresenterScope
    fun pokemonPresenter(getPokemons: GetPokemons) = PokemonPresenter(getPokemons)

}