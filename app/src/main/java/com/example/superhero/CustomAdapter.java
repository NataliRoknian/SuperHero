package com.example.superhero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superhero.Models.SuperHero;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {


    private List<SuperHero> superHeroes;
    private OnHeroClickedListener listener;
    public CustomAdapter(List<SuperHero> superHeroes, OnHeroClickedListener listener) {
        this.superHeroes = superHeroes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.superhero_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        SuperHero hero = superHeroes.get(position);
        holder.superHeroName.setText(hero.getName());
        if(hero.getImage() != null){
            Picasso.get().load(hero.getImage().getUrl()).into(holder.superHeroImage);
        }
        holder.itemView.setOnClickListener((v) -> listener.onClicked(hero));
    }

    @Override
    public int getItemCount() {
        return superHeroes.size();
    }
}
