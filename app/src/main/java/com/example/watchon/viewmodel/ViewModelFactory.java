package com.example.watchon.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.di.Injection;
import com.example.watchon.ui.detailfilm.DetailFilmViewModel;
import com.example.watchon.ui.favoritemovie.FavoriteMovieViewModel;
import com.example.watchon.ui.favoritetvshow.FavoriteTvShowViewModel;
import com.example.watchon.ui.movie.MovieListViewModel;
import com.example.watchon.ui.tvshow.TvShowListViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final WatchOnRepository watchOnRepository;

    public ViewModelFactory(WatchOnRepository watchOnRepository) {
        this.watchOnRepository = watchOnRepository;
    }

    public static ViewModelFactory getInstance(Context context){
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class){
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieListViewModel.class)){
            //noinspection unchecked
            return (T) new MovieListViewModel(watchOnRepository);
        }
        else if (modelClass.isAssignableFrom(TvShowListViewModel.class)){
            //noinspection unchecked
            return (T) new TvShowListViewModel(watchOnRepository);
        }
        else if (modelClass.isAssignableFrom(DetailFilmViewModel.class)){
            //noinspection unchecked
            return (T) new DetailFilmViewModel(watchOnRepository);
        }
        else if (modelClass.isAssignableFrom(FavoriteMovieViewModel.class)){
            //noinspection unchecked
            return (T) new FavoriteMovieViewModel(watchOnRepository);
        }
        else if (modelClass.isAssignableFrom(FavoriteTvShowViewModel.class)){
            //noinspection unchecked
            return (T) new FavoriteTvShowViewModel(watchOnRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
