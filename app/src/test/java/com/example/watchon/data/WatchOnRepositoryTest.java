package com.example.watchon.data;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.example.watchon.data.source.local.LocalDataSource;
import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.data.source.remote.RemoteDataSource;
import com.example.watchon.utils.AppExecutors;
import com.example.watchon.utils.DummyData;
import com.example.watchon.utils.LiveDataTestUtil;
import com.example.watchon.utils.PagedListUtil;
import com.example.watchon.vo.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;


public class WatchOnRepositoryTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final RemoteDataSource remoteDataSource = mock(RemoteDataSource.class);
    private final LocalDataSource localDataSource = Mockito.mock(LocalDataSource.class);
    private final AppExecutors appExecutors = Mockito.mock(AppExecutors.class);

    private final FakeWatchOnRepository fakeWatchOnRepository = new FakeWatchOnRepository(remoteDataSource, localDataSource, appExecutors);

    @Test
    public void getMoviesList() {
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getMovies()).thenReturn(dataSourceFactory);
        fakeWatchOnRepository.getMoviesList();

        Resource<PagedList<MovieEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateMovieEntities()));
        verify(localDataSource).getMovies();

        assertNotNull(movieEntities.data);
        assertEquals(5, movieEntities.data.size());
    }

    @Test
    public void getTvShowsList() {
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getTvShows()).thenReturn(dataSourceFactory);
        fakeWatchOnRepository.getTvShowsList();

        Resource<PagedList<TvShowEntity>> tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateTvShowEntities()));
        verify(localDataSource).getTvShows();

        assertNotNull(tvShowEntities.data);
        assertEquals(5, tvShowEntities.data.size());
    }

    @Test
    public void getDetailMovie() {
        MutableLiveData<MovieEntity> dummyMovie = new MutableLiveData<>();
        dummyMovie.setValue(DummyData.generateMovieEntity());
        int id = dummyMovie.getValue().getId();
        when(localDataSource.getMovieById(id)).thenReturn(dummyMovie);

        Resource<MovieEntity> movieEntity = LiveDataTestUtil.getValue(fakeWatchOnRepository.getDetailMovie(dummyMovie.getValue()));
        verify(localDataSource).getMovieById(id);
        assertNotNull(movieEntity);
        assertEquals(id, movieEntity.data.getId());
        assertEquals(dummyMovie.getValue().getTitle(), movieEntity.data.getTitle());
        assertEquals(dummyMovie.getValue().getOverview(), movieEntity.data.getOverview());
        assertEquals(dummyMovie.getValue().getReleaseDate(), movieEntity.data.getReleaseDate());
        assertEquals(dummyMovie.getValue().getPosterPath(), movieEntity.data.getPosterPath());
        assertEquals(dummyMovie.getValue().getGenre(), movieEntity.data.getGenre());
        assertEquals(dummyMovie.getValue().getRuntime(), movieEntity.data.getRuntime());
    }

    @Test
    public void getDetailTvShow() {
        MutableLiveData<TvShowEntity> dummyTvShow = new MutableLiveData<>();
        dummyTvShow.setValue(DummyData.generateTvShowEntity());
        int id = dummyTvShow.getValue().getId();
        when(localDataSource.getTvShowById(id)).thenReturn(dummyTvShow);

        Resource<TvShowEntity> tvShowEntity = LiveDataTestUtil.getValue(fakeWatchOnRepository.getDetailTvShow(dummyTvShow.getValue()));
        verify(localDataSource).getTvShowById(id);
        assertNotNull(tvShowEntity);
        assertEquals(id, tvShowEntity.data.getId());
        assertEquals(dummyTvShow.getValue().getTitle(), tvShowEntity.data.getTitle());
        assertEquals(dummyTvShow.getValue().getOverview(), tvShowEntity.data.getOverview());
        assertEquals(dummyTvShow.getValue().getReleaseDate(), tvShowEntity.data.getReleaseDate());
        assertEquals(dummyTvShow.getValue().getPosterPath(), tvShowEntity.data.getPosterPath());
        assertEquals(dummyTvShow.getValue().getGenre(), tvShowEntity.data.getGenre());
        assertEquals(dummyTvShow.getValue().getRuntime(), tvShowEntity.data.getRuntime());
    }

    @Test
    public void getFavoriteMovies(){
        DataSource.Factory<Integer, MovieEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getFavoriteMovies()).thenReturn(dataSourceFactory);
        fakeWatchOnRepository.getFavoriteMovie();

        Resource<PagedList<MovieEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateMovieEntities()));
        verify(localDataSource).getFavoriteMovies();
        assertNotNull(movieEntities);
        assertEquals(5, movieEntities.data.size());
    }

    @Test
    public void getFavoriteTvShow(){
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getFavoriteTvShows()).thenReturn(dataSourceFactory);
        fakeWatchOnRepository.getFavoriteTvShow();

        Resource<PagedList<TvShowEntity>> tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DummyData.generateTvShowEntities()));
        verify(localDataSource).getFavoriteTvShows();
        assertNotNull(tvShowEntities);
        assertEquals(5, tvShowEntities.data.size());
    }
}