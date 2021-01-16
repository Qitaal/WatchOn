package com.example.watchon.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.vo.Resource;

public class TvShowListViewModel extends ViewModel {
    private final WatchOnRepository watchOnRepository;

    public TvShowListViewModel(WatchOnRepository watchOnRepository) {
        this.watchOnRepository = watchOnRepository;
    }

    public LiveData<Resource<PagedList<TvShowEntity>>> getTvShows(){
        return watchOnRepository.getTvShowsList();
    }
}
