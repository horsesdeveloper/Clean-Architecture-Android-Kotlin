package com.horses.architecture.di.component

import com.horses.architecture.di.PresenterScope
import com.horses.architecture.di.module.PresenterModule
import com.horses.architecture.view.ui.activity.MainActivity
import com.horses.architecture.view.ui.activity.PokemonListActivity
import dagger.Component

/**
 * @author @briansalvattore on 01/03/2018.
 */
@PresenterScope
@Component(dependencies = [AppComponent::class], modules = [PresenterModule::class])
interface PresenterComponent {

    fun inject(activity: PokemonListActivity)
    fun inject(activity: MainActivity)
}