package com.example.superhero;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    TextView superHeroName;
    ImageView superHeroImage;
    CardView cardView;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);

        superHeroName = itemView.findViewById(R.id.superHeroName);
        superHeroImage = itemView.findViewById(R.id.superHeroImage);
        cardView = itemView.findViewById(R.id.mainContainer);
    }
}
