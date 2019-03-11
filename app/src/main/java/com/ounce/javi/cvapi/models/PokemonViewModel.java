package com.ounce.javi.cvapi.models;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class PokemonViewModel extends ViewModel {

    List<Pokemon> poemList = new ArrayList<>();

    public List<Pokemon> getPoemList(){
        return poemList;
    }
}
