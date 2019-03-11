package com.ounce.javi.cvapi.pokeapi;


import com.ounce.javi.cvapi.models.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokkeapiService {

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon" )
    Call<PokemonRespuesta> obtenerListaPokemontipos(@Query("limit") int limit, @Query("offset") int offset);

   // "type": {
    //    "name": "normal",type/3/
   @GET("type")
   Call<PokemonRespuesta> obtenerListaPokemonnormales(@Query("limit") int limit, @Query("offset") int offset,  @Query("name") String type);




}
