package com.ounce.javi.cvapi.models;

import java.util.ArrayList;

public class PokemonRespuesta {

     private ArrayList<Pokemon> results;


    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }

    public ArrayList<Pokemon> getResults() {
        return results;
    }
}
