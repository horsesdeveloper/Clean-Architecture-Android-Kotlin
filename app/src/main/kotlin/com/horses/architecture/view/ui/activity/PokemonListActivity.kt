package com.horses.architecture.view.ui.activity

import android.util.Log
import com.horses.architecture.R
import com.horses.architecture.view.presenter.PokemonPresenter
import com.horses.architecture.view.ui.base.HorsesBaseActivity
import com.horses.architecture.view.viewmodel.PokemonViewModel
import javax.inject.Inject

class PokemonListActivity : HorsesBaseActivity(), PokemonPresenter.View {

    @Inject
    lateinit var presenter: PokemonPresenter

    override fun getView() = R.layout.activity_pokemon_list

    override fun onCreate() {
        component.inject(this)

        presenter.attachView(this)
        presenter.getList()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showPokemons(list: List<PokemonViewModel>) {
        Log.d("PokemonListActivity", "showPokemons() called with: list = [ $list ]")
    }
}
