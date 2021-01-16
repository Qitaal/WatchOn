package com.example.watchon.ui.favoritetvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.TvShowEntity;

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
public class FavoriteTvShowViewModelTest {

    FavoriteTvShowViewModel favoriteTvShowViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private WatchOnRepository watchOnRepository;

    @Mock
    private Observer<PagedList<TvShowEntity>> observer;

    @Mock
    private PagedList<TvShowEntity> pagedList;

    @Before
    public void setUp(){
        favoriteTvShowViewModel = new FavoriteTvShowViewModel(watchOnRepository);
    }

    @Test
    public void getFavoriteTvShow() {
        PagedList<TvShowEntity> dummyTvShow = pagedList;

        when(dummyTvShow.size()).thenReturn(4);
        MutableLiveData<PagedList<TvShowEntity>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTvShow);

        when(watchOnRepository.getFavoriteTvShow()).thenReturn(tvShows);
        List<TvShowEntity> movieEntities = favoriteTvShowViewModel.getFavoriteTvShow().getValue();
        verify(watchOnRepository).getFavoriteTvShow();
        assertNotNull(movieEntities);
        assertEquals(4, movieEntities.size());

        favoriteTvShowViewModel.getFavoriteTvShow().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }
}