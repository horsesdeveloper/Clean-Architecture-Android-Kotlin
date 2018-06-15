package com.horses.architecture.view.viewmodel

import java.io.Serializable

data class PokemonViewModel(
        var name: String = "",
        var image: String = ""
) : Serializable