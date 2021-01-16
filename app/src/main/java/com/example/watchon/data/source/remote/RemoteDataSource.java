package com.example.watchon.data.source.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.watchon.data.source.remote.api.ApiService;
import com.example.watchon.data.source.remote.response.ApiResponse;
import com.example.watchon.utils.AppExecutors;
import com.example.watchon.utils.EspressoIdlingResource;
import com.example.watchon.data.source.remote.response.movie.detailmovie.DetailMovieResponse;
import com.example.watchon.data.source.remote.response.movie.movielist.MovieResponse;
import com.example.watchon.data.source.remote.response.tvshow.tvshowdetail.DetailTvShowResponse;
import com.example.watchon.data.source.remote.response.tvshow.tvshowlist.TvShowResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    private static final String TAG = "RemoteDataSource";

    private static RemoteDataSource INSTANCE;
    private final ApiService apiService;
    private final AppExecutors appExecutors;

    public RemoteDataSource(ApiService apiService, AppExecutors appExecutors) {
        this.apiService = apiService;
        this.appExecutors = appExecutors;
    }

    public static RemoteDataSource getInstance(ApiService apiService, AppExecutors appExecutors){
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(apiService, appExecutors);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<MovieResponse>> getMoviesList() {

        MutableLiveData<ApiResponse<MovieResponse>> moviesResponse = new MutableLiveData<>();

        EspressoIdlingResource.increment();
        appExecutors.networkIO().execute(() -> apiService.getMovies(ApiService.API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<MovieResponse> call, @NotNull Response<MovieResponse> response) {
                moviesResponse.postValue(ApiResponse.success(response.body()));
            }

            @Override
            public void onFailure(@NotNull Call<MovieResponse> call, @NotNull Throwable t) {
                moviesResponse.postValue(ApiResponse.error(t.getMessage(), null));
                Log.d(TAG, "onFailure: getMoviesList: " + t.getMessage());
            }
        }));

        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        return moviesResponse;
    }

    public LiveData<ApiResponse<TvShowResponse>> getTvShowsList(){

        MutableLiveData<ApiResponse<TvShowResponse>> tvShowResponse = new MutableLiveData<>();

        EspressoIdlingResource.increment();
        appExecutors.networkIO().execute(() -> apiService.getTvShows(ApiService.API_KEY).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NotNull Call<TvShowResponse> call, @NotNull Response<TvShowResponse> response) {
                tvShowResponse.postValue(ApiResponse.success(response.body()));
            }

            @Override
            public void onFailure(@NotNull Call<TvShowResponse> call, @NotNull Throwable t) {
                tvShowResponse.postValue(ApiResponse.error(t.getMessage(), null));
                Log.d(TAG, "onFailure: getTvShowList: " + t.getMessage());
            }
        }));

        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }
        return tvShowResponse;
    }

    public LiveData<ApiResponse<DetailMovieResponse>> getDetailMovie(int movieId){

        MutableLiveData<ApiResponse<DetailMovieResponse>> detailMovieResponse = new MutableLiveData<>();

        EspressoIdlingResource.increment();
        appExecutors.networkIO().execute(() -> apiService.getDetailMovie(String.valueOf(movieId), ApiService.API_KEY).enqueue(new Callback<DetailMovieResponse>() {
            @Override
            public void onResponse(@NotNull Call<DetailMovieResponse> call, @NotNull Response<DetailMovieResponse> response) {
                detailMovieResponse.postValue(ApiResponse.success(response.body()));
            }

            @Override
            public void onFailure(@NotNull Call<DetailMovieResponse> call, @NotNull Throwable t) {
                detailMovieResponse.postValue(ApiResponse.error(t.getMessage(), null));
                Log.d(TAG, "onFailure: getDetailMovie: " + t.getMessage());
            }
        }));

        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }
        return detailMovieResponse;
    }

    public LiveData<ApiResponse<DetailTvShowResponse>> getDetailTvShow(int tvShowId){

        MutableLiveData<ApiResponse<DetailTvShowResponse>> detailTvShowResponse = new MutableLiveData<>();

        EspressoIdlingResource.increment();
        appExecutors.networkIO().execute(() -> apiService.getDetailTvShow(String.valueOf(tvShowId), ApiService.API_KEY).enqueue(new Callback<DetailTvShowResponse>() {
            @Override
            public void onResponse(@NotNull Call<DetailTvShowResponse> call, @NotNull Response<DetailTvShowResponse> response) {
                detailTvShowResponse.postValue(ApiResponse.success(response.body()));
            }

            @Override
            public void onFailure(@NotNull Call<DetailTvShowResponse> call, @NotNull Throwable t) {
                detailTvShowResponse.postValue(ApiResponse.error(t.getMessage(), null));
                Log.d(TAG, "onFailure: getDetailTvShow: " + t.getMessage());
            }
        }));

        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
            EspressoIdlingResource.decrement();
        }
        return detailTvShowResponse;
    }
}
