package com.horses.architecture.data.retrofit

import com.horses.architecture.data.entity.PokemonResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {

    @GET("api/v2/pokemon")
    fun pokemonListRequest(): Observable<PokemonResponse>
}