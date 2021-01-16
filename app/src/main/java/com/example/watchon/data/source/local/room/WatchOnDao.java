package com.example.watchon.data.source.local.room;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.local.entity.TvShowEntity;

import java.util.List;

@Dao
public interface WatchOnDao {

    @Query("SELECT * FROM movie")
    DataSource.Factory<Integer, MovieEntity> getMovies();

    @Query("SELECT * FROM tv_show")
    DataSource.Factory<Integer, TvShowEntity> getTvShows();

    @Query("SELECT * FROM movie WHERE movie_id = :movieId")
    LiveData<MovieEntity> getMovieById(int movieId);

    @Query("SELECT * FROM tv_show WHERE tv_show_id = :tvShowId")
    LiveData<TvShowEntity> getTvShowById(int tvShowId);

    @Query("SELECT * FROM movie WHERE favorite = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteMovies();

    @Query("SELECT * FROM tv_show WHERE favorite = 1")
    DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MovieEntity> moviesList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShow(List<TvShowEntity> tvShowsList);

    @Update
    void updateMovie(MovieEntity movieEntity);

    @Update
    void updateTvShow(TvShowEntity tvShowEntity);
}
