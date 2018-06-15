package com.horses.architecture.data.repository

import com.horses.architecture.data.mapper.PokemonMapper
import com.horses.architecture.data.retrofit.ApiService
import com.horses.architecture.domain.model.Pokemon
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonsRepository(var apiService: ApiService) {

    private val pokemonMapper = PokemonMapper()

    fun getPokemonsList(): Observable<List<Pokemon>> = apiService.pokemonListRequest()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).map {
                input -> pokemonMapper.reverseMap(input)
            }
}


