package com.example.watchon.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.TvShowEntity;
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
public class TvShowListViewModelTest {

    TvShowListViewModel tvShowListViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private WatchOnRepository watchOnRepository;

    @Mock
    private Observer<Resource<PagedList<TvShowEntity>>> observer;

    @Mock
    private PagedList<TvShowEntity> pagedList;

    @Before
    public void setUp() {
        tvShowListViewModel = new TvShowListViewModel(watchOnRepository);
    }

    @Test
    public void getTvShows() {
        Resource<PagedList<TvShowEntity>> dummyTvShows = Resource.success(pagedList);
        when(dummyTvShows.data.size()).thenReturn(4);
        MutableLiveData<Resource<PagedList<TvShowEntity>>> tvShows = new MutableLiveData<>();
        tvShows.setValue(dummyTvShows);

        when(watchOnRepository.getTvShowsList()).thenReturn(tvShows);
        List<TvShowEntity> tvShowEntities = tvShowListViewModel.getTvShows().getValue().data;
        verify(watchOnRepository).getTvShowsList();

        assertNotNull(tvShowEntities);
        assertEquals(4, tvShowEntities.size());

        tvShowListViewModel.getTvShows().observeForever(observer);
        verify(observer).onChanged(dummyTvShows);
    }
}