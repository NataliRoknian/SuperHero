package com.example.superhero;

import androidx.lifecycle.MutableLiveData;

import com.example.superhero.Models.Appearance;
import com.example.superhero.Models.HeroSearchResponse;
import com.example.superhero.Models.SuperHero;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestManager {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build();
    HeroesService service = retrofit.create(HeroesService.class);

    public void getHeroesByName(String name,
                                MutableLiveData<List<SuperHero>> heroesData,
                                MutableLiveData<Throwable> exceptionsData) {
        Call<HeroSearchResponse> call = service.getHeroesByName(name);
        call.enqueue(new Callback<HeroSearchResponse>() {
            @Override
            public void onResponse(Call<HeroSearchResponse> call, Response<HeroSearchResponse> response) {
                if(response.isSuccessful() && response.body()!=null && response.body().getSuperHeroes()!=null)
                    heroesData.postValue(response.body().getSuperHeroes());
            }

            @Override
            public void onFailure(Call<HeroSearchResponse> call, Throwable t) {
                    exceptionsData.postValue(t);
            }
        });
    }

    public void getHeroesByIds(List<String> ids,
                               MutableLiveData<List<SuperHero>> heroesData,
                               MutableLiveData<Throwable> exceptionsData) {

        List<SuperHero> list = new ArrayList<>();
        List<Call<SuperHero>> calls = new ArrayList<>();
        for ( String id : ids) { calls.add(service.getHeroById(id));  }

        for (Call<SuperHero> call : calls) {
            call.enqueue(new Callback<SuperHero>() {
                @Override
                public void onResponse(Call<SuperHero> call, Response<SuperHero> response) {

                    if(response.isSuccessful() && response.body()!=null) {
                        if (call.equals(calls.get(calls.size() - 1))) {
                            list.add(response.body());
                            heroesData.postValue(list);
                        } else {
                            list.add(response.body());
                        }
                    }else  {
                        exceptionsData.postValue(new Throwable("There was a problem fetching heroes"));
                    }
                }

                @Override
                public void onFailure(Call<SuperHero> call, Throwable t) {
                    exceptionsData.postValue(t);
                }
            });
        }
    }

}
