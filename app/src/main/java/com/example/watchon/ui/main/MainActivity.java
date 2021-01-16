package com.example.watchon.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.watchon.R;
import com.example.watchon.databinding.ActivityMainBinding;
import com.example.watchon.ui.favorite.FavoriteActvity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        MainPagerAdapter mainPagerAdapter = new MainPagerAdapter(this, getSupportFragmentManager());

        activityMainBinding.viewPagerMain.setAdapter(mainPagerAdapter);
        activityMainBinding.tabLayoutMain.setupWithViewPager(activityMainBinding.viewPagerMain);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_favorite){
            startActivity(new Intent(this, FavoriteActvity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}