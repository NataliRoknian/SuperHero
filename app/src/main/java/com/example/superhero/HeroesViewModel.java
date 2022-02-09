package com.example.superhero;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.superhero.Models.SuperHero;

import java.util.ArrayList;
import java.util.List;

public class HeroesViewModel extends ViewModel {

    private MutableLiveData<List<SuperHero>> heroesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<SuperHero>> heroesSearchLiveData = new MutableLiveData<>();
    private MutableLiveData<Throwable> exceptionsLiveData = new MutableLiveData<>();


    private SuperHero detailsSuperHero;
    private RequestManager manager = new RequestManager();
    public HeroesViewModel() {
        List<String> defaultHeroes = new ArrayList<>(3);
        defaultHeroes.add("433");
        defaultHeroes.add("517");
        defaultHeroes.add("643");
        manager.getHeroesByIds(defaultHeroes,heroesLiveData,exceptionsLiveData);
    }

    public void setDetailsSuperHero(SuperHero detailsSuperHero) {
        this.detailsSuperHero = detailsSuperHero;
    }

    public SuperHero getDetailsSuperHero() {
        return detailsSuperHero;
    }

    public void getHeroesByName(String name) {
        manager.getHeroesByName(name,heroesSearchLiveData,exceptionsLiveData);
    }

    public MutableLiveData<List<SuperHero>> getHeroesLiveData() {
        return heroesLiveData;
    }

    public MutableLiveData<List<SuperHero>> getHeroesSearchLiveData() {
        return heroesSearchLiveData;
    }

    public MutableLiveData<Throwable> getExceptionsLiveData() {
        return exceptionsLiveData;
    }
}
