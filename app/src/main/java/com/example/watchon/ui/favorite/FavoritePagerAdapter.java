package com.example.watchon.ui.favorite;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.watchon.ui.favoritemovie.FavoriteMovieFragment;
import com.example.watchon.ui.favoritetvshow.FavoriteTvShowFragment;
import com.example.watchon.R;

public class FavoritePagerAdapter extends FragmentPagerAdapter {

    @StringRes
    public static int[] TAB_TITLES = new int[]{R.string.movie_title, R.string.tv_show_title};
    private final Context context;

    public FavoritePagerAdapter(Context context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FavoriteMovieFragment();
            case 1:
                return new FavoriteTvShowFragment();
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
