package com.example.watchon.ui.favoritetvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.TvShowEntity;

public class FavoriteTvShowViewModel extends ViewModel {

    private final WatchOnRepository watchOnRepository;

    public FavoriteTvShowViewModel(WatchOnRepository watchOnRepository) {
        this.watchOnRepository = watchOnRepository;
    }

    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShow(){
        return watchOnRepository.getFavoriteTvShow();
    }

    public void setFavorite(TvShowEntity tvShowEntity){
        watchOnRepository.setFavoriteTvShow(tvShowEntity, !tvShowEntity.isFavorite());
    }
}
