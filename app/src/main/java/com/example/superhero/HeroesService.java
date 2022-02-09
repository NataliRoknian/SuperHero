package com.example.superhero;

import com.example.superhero.Models.Appearance;
import com.example.superhero.Models.HeroSearchResponse;
import com.example.superhero.Models.SuperHero;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HeroesService {

    @GET("/api/10224157378116527/{id}")
    Call<SuperHero> getHeroById(@Path("id") String id);

    @GET("/api/10224157378116527/search/{name}")
    Call<HeroSearchResponse> getHeroesByName(@Path("name") String name);



}
