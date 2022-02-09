package com.example.superhero;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.superhero.Models.SuperHero;
import com.example.superhero.databinding.FragmentHeroDetailsBinding;
import com.squareup.picasso.Picasso;

public class HeroDetailsFragment extends Fragment {

    private HeroesViewModel heroesViewModel;
    private FragmentHeroDetailsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        heroesViewModel = new ViewModelProvider(getActivity()).get(HeroesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHeroDetailsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView heroImageIv;
        TextView heroNameTv,heroBioTv,heroAffiliationTv,heroWorkTv,heroPowersTv;
        ImageButton shareBtn;
        SuperHero hero = heroesViewModel.getDetailsSuperHero();
        heroImageIv = binding.heroImageIv;
        heroNameTv = binding.heroNameTv;
        heroBioTv = binding.heroBioTv;
        heroAffiliationTv = binding.heroAffiliationTv;
        heroWorkTv = binding.heroWorkTv;
        heroPowersTv = binding.heroPowersTv;
        Picasso.get().load(hero.getImage().getUrl()).into(heroImageIv);
        heroNameTv.setText(hero.getName());
        StringBuilder bioSb = new StringBuilder();
        bioSb.append("Alter Egos: ")
         .append(hero.getBiography()
         .getAlterEgos())
        .append("\n")
         .append("Place of birth: ")
        .append(hero.getBiography().getPlaceOfBirth())
        .append("\n")
        .append("First Appearance: ")
        .append(hero.getBiography().getFirstAppearance());
        heroBioTv.setText(bioSb.toString());
        StringBuilder affiliationSb = new StringBuilder();
        affiliationSb.append("Relatives: ")
                .append(hero.getConnections()
                        .getRelatives())
                .append("\n")
                .append("Group: ")
                .append(hero.getConnections().getGroupAffiliation());
        heroAffiliationTv.setText(affiliationSb.toString());

        StringBuilder workSb = new StringBuilder();
        workSb.append("Base: ")
                .append(hero.getWork()
                        .getBase())
                .append("\n")
                .append("Occupation: ")
                .append(hero.getWork().getOccupation());
        heroWorkTv.setText(workSb.toString());

        StringBuilder powersSb = new StringBuilder();
        powersSb.append("Super powers: ")
                .append(hero.getPowerstats()
                        .getPower())
                .append("\n")
                .append("Speed: ")
                .append(hero.getPowerstats().getSpeed())
                .append("\n")
                .append("Intelligence: ")
                .append(hero.getPowerstats().getIntelligence());
        heroPowersTv.setText(powersSb.toString());

        StringBuilder heroShare = new StringBuilder();
        heroShare.append("This is ")
                .append(hero.getName())
                .append(" SuperHero")
                .append("\n")
                .append(bioSb);


        shareBtn = binding.shareButton;
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent;
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SuperHeroes App");
                shareIntent.putExtra(Intent.EXTRA_TEXT, heroShare.toString());
                startActivity(Intent.createChooser(shareIntent, "Share via"));

            }
        });

    }
}