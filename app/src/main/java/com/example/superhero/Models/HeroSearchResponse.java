package com.example.superhero.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HeroSearchResponse {

    private String response;
    @SerializedName("results")
    private List<SuperHero> superHeroes;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<SuperHero> getSuperHeroes() {
        return superHeroes;
    }

    public void setSuperHeroes(List<SuperHero> superHeroes) {
        this.superHeroes = superHeroes;
    }
}
