package com.example.watchon.ui.detailfilm;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.model.FilmDetail;
import com.example.watchon.utils.DummyData;
import com.example.watchon.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailTvShowViewModelTest {
    private DetailFilmViewModel detailFilmViewModel;
    private final TvShowEntity dummyTvShoEntity = DummyData.generateTvShowEntity();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private WatchOnRepository watchOnRepository;

    @Mock
    private Observer<FilmDetail> observer;

    @Before
    public void setUp(){
        detailFilmViewModel = new DetailFilmViewModel(watchOnRepository);
    }

    @Test
    public void getSelectedMovie(){
        Resource<TvShowEntity> dummyTvShow = Resource.success(dummyTvShoEntity);
        MutableLiveData<Resource<TvShowEntity>> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyTvShow);

        when(watchOnRepository.getDetailTvShow(dummyTvShoEntity)).thenReturn(tvShow);
        detailFilmViewModel.setSelectedTvShow(dummyTvShoEntity);
        verify(watchOnRepository).getDetailTvShow(dummyTvShoEntity);

        FilmDetail filmDetail = detailFilmViewModel.getDetailFilm().getValue();

        assertNotNull(filmDetail);
        assertEquals(dummyTvShoEntity.getId(), filmDetail.getId());
        assertEquals(dummyTvShoEntity.getTitle(), filmDetail.getTitle());
        assertEquals(dummyTvShoEntity.getGenre(), filmDetail.getGenre());
        assertEquals(dummyTvShoEntity.getPosterPath(), filmDetail.getPosterPath());
        assertEquals(dummyTvShoEntity.isFavorite(), filmDetail.isFavorite());
        assertEquals(dummyTvShoEntity.getReleaseDate(), filmDetail.getReleaseDate());
        assertEquals(dummyTvShoEntity.getOverview(), filmDetail.getOverview());

        detailFilmViewModel.getDetailFilm().observeForever(observer);
        verify(observer).onChanged(filmDetail);
    }
}
