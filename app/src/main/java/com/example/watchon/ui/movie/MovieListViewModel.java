package com.example.watchon.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.vo.Resource;

public class MovieListViewModel extends ViewModel {
    private final WatchOnRepository watchOnRepository;

    public MovieListViewModel(WatchOnRepository watchOnRepository) {
        this.watchOnRepository = watchOnRepository;
    }

    public LiveData<Resource<PagedList<MovieEntity>>> getMovies(){
        return watchOnRepository.getMoviesList();
    }
}
