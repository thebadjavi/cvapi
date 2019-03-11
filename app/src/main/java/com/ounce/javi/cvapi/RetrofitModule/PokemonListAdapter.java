package com.ounce.javi.cvapi.RetrofitModule;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ounce.javi.cvapi.R;
import com.ounce.javi.cvapi.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonListViewHolder>{
    public List<Pokemon> pokemonList = new ArrayList<>();

    @NonNull
    @Override
    public PokemonListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonListViewHolder(view);
    }


  @Override
    public void onBindViewHolder(@NonNull PokemonListViewHolder holder, int position) {
        Pokemon pokemon = pokemonList.get(position);

        holder.title.setText(pokemon.getName());
       // GlideApp.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" ).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    class PokemonListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        public PokemonListViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.nombreTextView);
            poster = itemView.findViewById(R.id.tipo);
        }
    }
}
