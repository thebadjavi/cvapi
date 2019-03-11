package com.ounce.javi.cvapi.RetrofitModule;

import com.ounce.javi.cvapi.models.Pokemon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemondbAPI {
    @GET("pokemon/1/")
    Call<PokemonList> getPokemons();
}
