package com.ounce.javi.cvapi;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ounce.javi.cvapi.models.Pokemon;
import com.ounce.javi.cvapi.models.PokemonRespuesta;
import com.ounce.javi.cvapi.models.PokemonViewModel;
import com.ounce.javi.cvapi.pokeapi.PokkeapiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TabAFragment extends Fragment {
  private static final String TAG = "POKEDEX";

  private Retrofit retrofit;

  private RecyclerView recyclerView;
  private ListaPokemonAdapter listaPokemonAdapter;

  private int offset;

  private boolean aptoParaCargar;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);


  }

  List<Pokemon> pokemonList;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_tab_a, container, false);



    recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
    listaPokemonAdapter = new ListaPokemonAdapter(this);
    recyclerView.setAdapter(listaPokemonAdapter);
    recyclerView.setHasFixedSize(true);
    final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy > 0) {
          int visibleItemCount = layoutManager.getChildCount();
          int totalItemCount = layoutManager.getItemCount();
          int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

          if (aptoParaCargar) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
              Log.i(TAG, " Llegamos al final.");

              aptoParaCargar = false;
              offset += 20;
              obtenerDatos(offset);
            }
          }
        }
      }
    });


    retrofit = new Retrofit.Builder()
            .baseUrl("http://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    aptoParaCargar = true;
    offset = 0;
    obtenerDatos(offset);

    return v;
  }

  private void obtenerDatos(int offset) {
    PokkeapiService service = retrofit.create(PokkeapiService.class);
    Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon (20, offset);


    pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
      @Override
      public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
        aptoParaCargar = true;
        if (response.isSuccessful()) {

          PokemonRespuesta pokemonRespuesta = response.body();
          ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();

          listaPokemonAdapter.adicionarListaPokemon(listaPokemon);

        } else {
          Log.e(TAG, " onResponse: " + response.errorBody());
        }
      }

      @Override
      public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
        aptoParaCargar = true;
        Log.e(TAG, " onFailure: " + t.getMessage());
      }
    });

  }
}