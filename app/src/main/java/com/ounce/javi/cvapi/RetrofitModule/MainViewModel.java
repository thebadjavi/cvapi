package com.ounce.javi.cvapi.RetrofitModule;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.ounce.javi.cvapi.models.Pokemon;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private PokemondbRepository pokemondbRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        pokemondbRepository = new PokemondbRepository();
    }

    public LiveData<List<Pokemon>> getPokemons(){
        return pokemondbRepository.getPokemons();
    }
}
