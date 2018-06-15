package com.horses.architecture.domain.usecase

import com.horses.architecture.data.repository.PokemonsRepository
import com.horses.architecture.domain.model.Pokemon
import com.horses.architecture.domain.usecase.base.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetPokemons(
        executorThread: Scheduler,
        uiThread: Scheduler,
        var repository: PokemonsRepository
) : UseCase<List<Pokemon>>(executorThread, uiThread) {

    override fun createObservableUseCase(): Observable<List<Pokemon>> = repository.getPokemonsList()

}