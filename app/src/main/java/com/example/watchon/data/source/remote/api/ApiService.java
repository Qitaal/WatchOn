package com.example.watchon.data.source.remote.api;

import com.example.watchon.data.source.remote.response.movie.detailmovie.DetailMovieResponse;
import com.example.watchon.data.source.remote.response.movie.movielist.MovieResponse;
import com.example.watchon.data.source.remote.response.tvshow.tvshowdetail.DetailTvShowResponse;
import com.example.watchon.data.source.remote.response.tvshow.tvshowlist.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String API_KEY = "ac1c9cc272aa0baf4aa3f741dbd680b0";

    @GET("trending/movie/day")
    Call<MovieResponse> getMovies(@Query("api_key") String API_KEY);

    @GET("trending/tv/day")
    Call<TvShowResponse> getTvShows(@Query("api_key") String API_KEY);

    @GET("movie/{movie_id}")
    Call<DetailMovieResponse> getDetailMovie(
            @Path("movie_id") String movieId,
            @Query("api_key") String API_KEY
    );

    @GET("tv/{tv_id}")
    Call<DetailTvShowResponse> getDetailTvShow(
            @Path("tv_id") String tvId,
            @Query("api_key") String API_KEY
    );
}
