package com.example.watchon.ui.favorite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.watchon.databinding.ActivityFavoriteBinding;

public class FavoriteActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityFavoriteBinding activityFavoriteBinding = ActivityFavoriteBinding.inflate(getLayoutInflater());
        setContentView(activityFavoriteBinding.getRoot());

        if(getSupportActionBar() != null)
            getSupportActionBar().setTitle("Favorite");

        FavoritePagerAdapter favoritePagerAdapter = new FavoritePagerAdapter(this, getSupportFragmentManager());
        activityFavoriteBinding.viewPagerFavorite.setAdapter(favoritePagerAdapter);
        activityFavoriteBinding.tabLayoutFavorite.setupWithViewPager(activityFavoriteBinding.viewPagerFavorite);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }
}