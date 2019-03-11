package com.ounce.javi.cvapi.RetrofitModule;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.ounce.javi.cvapi.models.Pokemon;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class PokemondbRepository {

    PokemondbAPI pokemondbAPI;

    public PokemondbRepository(){
        pokemondbAPI = PokemondbModule.getAPI();
    }

    public LiveData<List<Pokemon>> getPokemons(){
        final MutableLiveData<List<Pokemon>> lista = new MutableLiveData<>();

        pokemondbAPI.getPokemons().enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                lista.setValue(response.body().results);
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
            }
        });

        return lista;
    }
}
