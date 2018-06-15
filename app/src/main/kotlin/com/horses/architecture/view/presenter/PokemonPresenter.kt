package com.horses.architecture.view.presenter

import com.horses.architecture.data.mapper.PokemonMapper
import com.horses.architecture.domain.model.Pokemon
import com.horses.architecture.domain.usecase.GetPokemons
import com.horses.architecture.view.presenter.base.BasePresenter
import com.horses.architecture.view.viewmodel.PokemonViewModel
import io.reactivex.observers.DisposableObserver

class PokemonPresenter(var getPokemons: GetPokemons) : BasePresenter<PokemonPresenter.View>() {

    fun getList() {
        getPokemons.execute(object : DisposableObserver<List<Pokemon>>() {
            override fun onComplete() {
                view?.hideLoading()
            }

            override fun onNext(pokemons: List<Pokemon>) {

                val pokemonMapper = PokemonMapper()

                view?.showPokemons(pokemonMapper.toViewModel(pokemons))
            }

            override fun onError(e: Throwable) {
                view?.let {
                    it.hideLoading()
                    it.showError(e.message ?: "Error, try again")
                }
            }
        })
    }

    override fun detachView() {
        getPokemons.dispose()
        super.detachView()
    }

    interface View : BasePresenter.View {
        fun showPokemons(list: List<PokemonViewModel>)
    }
}