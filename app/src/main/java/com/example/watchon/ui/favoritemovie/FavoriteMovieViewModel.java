package com.example.watchon.ui.favoritemovie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.MovieEntity;

public class FavoriteMovieViewModel extends ViewModel {

    private final WatchOnRepository watchOnRepository;

    public FavoriteMovieViewModel(WatchOnRepository watchOnRepository) {
        this.watchOnRepository = watchOnRepository;
    }

    public LiveData<PagedList<MovieEntity>> getFavoriteMovie(){
        return watchOnRepository.getFavoriteMovie();
    }

    public void setFavorite(MovieEntity movieEntity){
        watchOnRepository.setFavoriteMovie(movieEntity, !movieEntity.isFavorite());
    }
}
