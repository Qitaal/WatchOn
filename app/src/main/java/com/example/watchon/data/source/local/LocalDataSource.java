package com.example.watchon.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.data.source.local.room.WatchOnDao;

import java.util.List;

public class LocalDataSource {

    private static LocalDataSource INSTANCE;
    private final WatchOnDao watchOnDao;

    public LocalDataSource(WatchOnDao watchOnDao) {
        this.watchOnDao = watchOnDao;
    }

    public static LocalDataSource getInstance(WatchOnDao watchOnDao){
        if (INSTANCE == null){
            INSTANCE = new LocalDataSource(watchOnDao);
        }
        return INSTANCE;
    }

    public DataSource.Factory<Integer, MovieEntity> getMovies(){
        return watchOnDao.getMovies();
    }

    public DataSource.Factory<Integer, TvShowEntity> getTvShows(){
        return watchOnDao.getTvShows();
    }

    public LiveData<MovieEntity> getMovieById(int movieId){
        return watchOnDao.getMovieById(movieId);
    }

    public LiveData<TvShowEntity> getTvShowById(int tvShowId){
        return watchOnDao.getTvShowById(tvShowId);
    }

    public DataSource.Factory<Integer, MovieEntity> getFavoriteMovies(){
        return watchOnDao.getFavoriteMovies();
    }

    public DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShows(){
        return watchOnDao.getFavoriteTvShows();
    }

    public void insertMovies(List<MovieEntity> moviesList){
        watchOnDao.insertMovies(moviesList);
    }

    public void insertTvShow(List<TvShowEntity> tvShowsList){
        watchOnDao.insertTvShow(tvShowsList);
    }

    public void updateMovie(MovieEntity movieEntity){
        watchOnDao.updateMovie(movieEntity);
    }

    public void updateTvShow(TvShowEntity tvShowEntity){
        watchOnDao.updateTvShow(tvShowEntity);
    }
}
