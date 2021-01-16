package com.example.watchon.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.watchon.ui.movie.MovieListFragment;
import com.example.watchon.R;
import com.example.watchon.ui.tvshow.TvShowListFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    public static final int[] TAB_TITLES = new int[]{R.string.movie_title, R.string.tv_show_title};
    private final Context context;

    public MainPagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new MovieListFragment();
            case 1:
                return new TvShowListFragment();
            default:
                return new Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
