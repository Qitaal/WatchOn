package com.example.watchon.data;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.vo.Resource;

public interface WatchOnDataSource {
    LiveData<Resource<PagedList<MovieEntity>>> getMoviesList();

    LiveData<Resource<PagedList<TvShowEntity>>> getTvShowsList();

    LiveData<Resource<MovieEntity>> getDetailMovie(MovieEntity movieEntity);

    LiveData<Resource<TvShowEntity>> getDetailTvShow(TvShowEntity tvShowEntity);

    LiveData<PagedList<MovieEntity>> getFavoriteMovie();

    LiveData<PagedList<TvShowEntity>> getFavoriteTvShow();

    void setFavoriteMovie(MovieEntity favoriteMovie, boolean state);

    void setFavoriteTvShow(TvShowEntity favoriteTvShow, boolean state);
}
