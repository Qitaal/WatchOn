package com.example.watchon.ui.detailfilm;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.model.FilmDetail;
import com.example.watchon.utils.DummyData;
import com.example.watchon.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailMovieViewModelTest {
    private DetailFilmViewModel detailFilmViewModel;
    private final MovieEntity dummyMovieEntity = DummyData.generateMovieEntity();

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
        Resource<MovieEntity> dummyMovie = Resource.success(dummyMovieEntity);
        MutableLiveData<Resource<MovieEntity>> movie = new MutableLiveData<>();
        movie.setValue(dummyMovie);

        when(watchOnRepository.getDetailMovie(dummyMovieEntity)).thenReturn(movie);
        detailFilmViewModel.setSelectedMovie(dummyMovieEntity);
        verify(watchOnRepository).getDetailMovie(dummyMovieEntity);

        FilmDetail filmDetail = detailFilmViewModel.getDetailFilm().getValue();

        assertNotNull(filmDetail);
        assertEquals(dummyMovieEntity.getId(), filmDetail.getId());
        assertEquals(dummyMovieEntity.getTitle(), filmDetail.getTitle());
        assertEquals(dummyMovieEntity.getGenre(), filmDetail.getGenre());
        assertEquals(dummyMovieEntity.getPosterPath(), filmDetail.getPosterPath());
        assertEquals(dummyMovieEntity.isFavorite(), filmDetail.isFavorite());
        assertEquals(dummyMovieEntity.getReleaseDate(), filmDetail.getReleaseDate());
        assertEquals(dummyMovieEntity.getOverview(), filmDetail.getOverview());

        detailFilmViewModel.getDetailFilm().observeForever(observer);
        verify(observer).onChanged(filmDetail);
    }
}