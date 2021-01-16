package com.example.watchon.ui.favoritemovie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.MovieEntity;

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
public class FavoriteMovieViewModelTest {

    FavoriteMovieViewModel favoriteMovieViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private WatchOnRepository watchOnRepository;

    @Mock
    private Observer<PagedList<MovieEntity>> observer;

    @Mock
    private PagedList<MovieEntity> pagedList;

    @Before
    public void setUp() {
        favoriteMovieViewModel = new FavoriteMovieViewModel(watchOnRepository);
    }

    @Test
    public void getFavoriteMovie() {
        PagedList<MovieEntity> dummyMovies = pagedList;

        when(dummyMovies.size()).thenReturn(4);
        MutableLiveData<PagedList<MovieEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(watchOnRepository.getFavoriteMovie()).thenReturn(movies);
        List<MovieEntity> movieEntities = favoriteMovieViewModel.getFavoriteMovie().getValue();
        verify(watchOnRepository).getFavoriteMovie();
        assertNotNull(movieEntities);
        assertEquals(4, movieEntities.size());

        favoriteMovieViewModel.getFavoriteMovie().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}