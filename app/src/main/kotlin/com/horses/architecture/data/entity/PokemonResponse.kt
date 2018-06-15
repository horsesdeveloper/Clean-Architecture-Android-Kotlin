package com.horses.architecture.data.entity

import java.io.Serializable

data class PokemonResponse(
        var count: Int,
        var results: List<PokemonItem>
) : Serializable {
    data class PokemonItem(
            var url: String,
            var name: String
    ) : Serializable
}