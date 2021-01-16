package com.example.watchon.data;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.watchon.data.source.local.LocalDataSource;
import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.data.source.remote.RemoteDataSource;
import com.example.watchon.data.source.remote.response.ApiResponse;
import com.example.watchon.data.source.remote.response.movie.detailmovie.DetailMovieResponse;
import com.example.watchon.data.source.remote.response.movie.movielist.MovieResponse;
import com.example.watchon.data.source.remote.response.movie.movielist.ResultsItemMovie;
import com.example.watchon.data.source.remote.response.tvshow.tvshowdetail.DetailTvShowResponse;
import com.example.watchon.data.source.remote.response.tvshow.tvshowlist.ResultsItemTvShow;
import com.example.watchon.data.source.remote.response.tvshow.tvshowlist.TvShowResponse;
import com.example.watchon.utils.AppExecutors;
import com.example.watchon.utils.JsonHelper;
import com.example.watchon.vo.Resource;

import java.util.ArrayList;

public class WatchOnRepository implements WatchOnDataSource{

    private volatile static WatchOnRepository INSTANCE = null;
    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    public WatchOnRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
    }

    public static WatchOnRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors){
        if (INSTANCE == null) {
            synchronized (WatchOnRepository.class){
                if (INSTANCE == null){
                    INSTANCE = new WatchOnRepository(remoteDataSource, localDataSource, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getMoviesList() {
        return new NetworkBoundResource<PagedList<MovieEntity>, MovieResponse>(appExecutors) {
            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getMovies(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> movieEntities) {
                return (movieEntities == null) || (movieEntities.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<MovieResponse>> createCall() {
                return remoteDataSource.getMoviesList();
            }

            @Override
            protected void saveCallResult(MovieResponse body) {
                ArrayList<MovieEntity> movieEntities = new ArrayList<>();
                for (ResultsItemMovie resultsItemMovie : body.getResults()){
                    movieEntities.add(new MovieEntity(
                            resultsItemMovie.getId(),
                            resultsItemMovie.getTitle(),
                            resultsItemMovie.getReleaseDate(),
                            null,
                            resultsItemMovie.getPosterPath(),
                            0,
                            null,
                            false
                    ));
                }
                localDataSource.insertMovies(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TvShowEntity>>> getTvShowsList() {
        return new NetworkBoundResource<PagedList<TvShowEntity>, TvShowResponse>(appExecutors) {
            @Override
            protected LiveData<PagedList<TvShowEntity>> loadFromDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getTvShows(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowEntity> tvShowEntityList) {
                return (tvShowEntityList == null) || (tvShowEntityList.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<TvShowResponse>> createCall() {
                return remoteDataSource.getTvShowsList();
            }

            @Override
            protected void saveCallResult(TvShowResponse body) {
                ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();
                for (ResultsItemTvShow resultsItemTvShow : body.getResults()){
                    tvShowEntities.add(new TvShowEntity(
                            resultsItemTvShow.getId(),
                            resultsItemTvShow.getName(),
                            resultsItemTvShow.getFirstAirDate(),
                            null,
                            resultsItemTvShow.getPosterPath(),
                            0,
                            null,
                            false
                    ));
                }
                localDataSource.insertTvShow(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getDetailMovie(MovieEntity movieEntity) {
        return new NetworkBoundResource<MovieEntity, DetailMovieResponse>(appExecutors) {
            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localDataSource.getMovieById(movieEntity.getId());
            }

            @Override
            protected Boolean shouldFetch(MovieEntity movieEntity) {
                return (movieEntity.getGenre() == null) || (movieEntity.getOverview() == null) || (movieEntity.getRuntime() == 0);
            }

            @Override
            protected LiveData<ApiResponse<DetailMovieResponse>> createCall() {
                return remoteDataSource.getDetailMovie(movieEntity.getId());
            }

            @Override
            protected void saveCallResult(DetailMovieResponse body) {
                movieEntity.setGenre(JsonHelper.movieGenreListToString(body.getGenres()));
                movieEntity.setOverview(body.getOverview());
                movieEntity.setRuntime(body.getRuntime());

                localDataSource.updateMovie(movieEntity);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowEntity>> getDetailTvShow(TvShowEntity tvShowEntity) {
        return new NetworkBoundResource<TvShowEntity, DetailTvShowResponse>(appExecutors) {
            @Override
            protected LiveData<TvShowEntity> loadFromDB() {
                return localDataSource.getTvShowById(tvShowEntity.getId());
            }

            @Override
            protected Boolean shouldFetch(TvShowEntity tvShowEntity) {
                return (tvShowEntity.getGenre() == null) || (tvShowEntity.getOverview() == null) || (tvShowEntity.getRuntime() == 0);
            }

            @Override
            protected LiveData<ApiResponse<DetailTvShowResponse>> createCall() {
                return remoteDataSource.getDetailTvShow(tvShowEntity.getId());
            }

            @Override
            protected void saveCallResult(DetailTvShowResponse body) {
                tvShowEntity.setGenre(JsonHelper.tvShowGenreListToString(body.getGenres()));
                tvShowEntity.setOverview(body.getOverview());
                tvShowEntity.setRuntime(body.getEpisodeRunTime().get(0));

                localDataSource.updateTvShow(tvShowEntity);
            }
        }.asLiveData();
    }


    @Override
    public LiveData<PagedList<MovieEntity>> getFavoriteMovie() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoriteMovies(), config).build();    }

    @Override
    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShow() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();
        return new LivePagedListBuilder<>(localDataSource.getFavoriteTvShows(), config).build();    }

    @Override
    public void setFavoriteMovie(MovieEntity favoriteMovie, boolean state) {
        favoriteMovie.setFavorite(state);
        appExecutors.diskIO().execute(() -> localDataSource.updateMovie(favoriteMovie));

    }

    @Override
    public void setFavoriteTvShow(TvShowEntity favoriteTvShow, boolean state) {
        favoriteTvShow.setFavorite(state);
        appExecutors.diskIO().execute(() -> localDataSource.updateTvShow(favoriteTvShow));
    }
}
