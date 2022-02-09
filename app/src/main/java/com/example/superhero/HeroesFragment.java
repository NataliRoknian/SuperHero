package com.example.superhero;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.superhero.Models.SuperHero;
import com.example.superhero.databinding.FragmentHeroesBinding;

import java.util.ArrayList;
import java.util.List;

public class HeroesFragment extends Fragment implements OnHeroClickedListener {


    private RecyclerView recyclerView;
    private CustomAdapter adapter;


    private RecyclerView searchRv;
    private CustomAdapter searchAdapter;


    private FragmentHeroesBinding binding;
    private HeroesViewModel heroesViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        heroesViewModel = new ViewModelProvider(getActivity()).get(HeroesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHeroesBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SearchView searchView = binding.searchView;
        setupSearchView(searchView);
        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("SuperHeroes");
        dialog.setMessage("Loading Heroes..");
        dialog.setCancelable(false);
        dialog.show();
        heroesViewModel.getHeroesLiveData().observe(getViewLifecycleOwner(), superHeroes -> {
            showSuperHeroes(superHeroes);
            dialog.dismiss();
        });
        heroesViewModel.getHeroesSearchLiveData().observe(getViewLifecycleOwner(), this::showSearchSuperHeroes);
    }

    private void setupSearchView(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(query.isEmpty()) return false;
                heroesViewModel.getHeroesByName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showSearchSuperHeroes(List<SuperHero> list) {
        searchRv = binding.searchRv;
        searchRv.setHasFixedSize(true);
        searchRv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        searchAdapter = new CustomAdapter(list,this);
        searchRv.setAdapter(searchAdapter);
    }

    private void showSuperHeroes(List<SuperHero> list) {
        recyclerView = binding.myRecycler;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new CustomAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClicked(SuperHero hero) {
        heroesViewModel.setDetailsSuperHero(hero);
        Navigation.findNavController(binding.getRoot())
                .navigate(R.id.action_heroesFragment_to_heroDetailsFragment);
    }
}