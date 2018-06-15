package com.horses.architecture.domain.model

import java.io.Serializable

data class Pokemon(
        var url: String = "",
        var name: String = ""
) : Serializable