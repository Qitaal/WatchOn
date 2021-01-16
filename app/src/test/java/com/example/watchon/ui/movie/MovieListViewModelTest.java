package com.example.watchon.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieListViewModelTest {

    MovieListViewModel movieListViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private WatchOnRepository watchOnRepository;

    @Mock
    private Observer<Resource<PagedList<MovieEntity>>> observer;

    @Mock
    private PagedList<MovieEntity> pagedList;

    @Before
    public void setUp(){
        movieListViewModel = new MovieListViewModel(watchOnRepository);
    }

    @Test
    public void getMovies() {
        Resource<PagedList<MovieEntity>> dummyMovies = Resource.success(pagedList);

        when(dummyMovies.data.size()).thenReturn(4);
        MutableLiveData<Resource<PagedList<MovieEntity>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(watchOnRepository.getMoviesList()).thenReturn(movies);
        List<MovieEntity> movieEntities = movieListViewModel.getMovies().getValue().data;
        verify(watchOnRepository).getMoviesList();

        assertNotNull(movieEntities);
        assertEquals(4, movieEntities.size());

        movieListViewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}