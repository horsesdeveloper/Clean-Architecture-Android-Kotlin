package com.horses.architecture.data.mapper

import com.horses.architecture.data.entity.PokemonResponse
import com.horses.architecture.domain.model.Pokemon
import com.horses.architecture.view.viewmodel.PokemonViewModel

class PokemonMapper {

    private fun toViewModel(value: Pokemon) : PokemonViewModel  {
        return PokemonViewModel().apply {
            this.name = value.name
            this.image = value.url
        }
    }

    fun toViewModel(values: List<Pokemon>): List<PokemonViewModel> {
        val returnValues = java.util.ArrayList<PokemonViewModel>(values.size)
        for (value in values) {
            returnValues.add(toViewModel(value))
        }
        return returnValues
    }

    fun reverseMap(input: PokemonResponse): List<Pokemon> {
        val output = ArrayList<Pokemon>()

        for (item in input.results) {
            output.add(Pokemon().apply {
                name = item.name
                url = funGetUriFromPath(item.url)
            })
        }

        return output
    }

    private fun funGetUriFromPath(url: String): String {
        val parts = url.split("/")
        val id = parts[parts.size - 2]

        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    }
}