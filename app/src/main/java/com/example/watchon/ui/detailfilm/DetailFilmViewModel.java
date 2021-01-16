package com.example.watchon.ui.detailfilm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.watchon.data.WatchOnRepository;
import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.local.entity.TvShowEntity;
import com.example.watchon.model.FilmDetail;
public class DetailFilmViewModel extends ViewModel {
    private final WatchOnRepository watchOnRepository;
    private MovieEntity movieEntity;
    private TvShowEntity tvShowEntity;
    private MutableLiveData<FilmDetail> detailFilm;

    public DetailFilmViewModel(WatchOnRepository watchOnRepository) {
        this.watchOnRepository = watchOnRepository;
        detailFilm = new MutableLiveData<>();
    }

    public void setDetailFilm(FilmDetail detailFilm){
        this.detailFilm.setValue(detailFilm);
    }

    public void setSelectedMovie(MovieEntity movieEntity){
        this.movieEntity = movieEntity;

        watchOnRepository.getDetailMovie(movieEntity).observeForever(movieEntityResource -> {
            if (movieEntityResource.data != null){
                setDetailFilm(new FilmDetail(
                        movieEntityResource.data.getId(),
                        movieEntityResource.data.getTitle(),
                        movieEntityResource.data.getReleaseDate(),
                        movieEntityResource.data.getGenre(),
                        movieEntityResource.data.getPosterPath(),
                        movieEntityResource.data.getRuntime(),
                        movieEntityResource.data.getOverview(),
                        movieEntityResource.data.isFavorite()
                ));
            }
        });
    }

    public void setSelectedTvShow(TvShowEntity tvShowEntity){
        this.tvShowEntity = tvShowEntity;

        watchOnRepository.getDetailTvShow(tvShowEntity).observeForever(tvShowEntityResource -> {
            if (tvShowEntityResource.data != null){
                setDetailFilm(new FilmDetail(
                        tvShowEntityResource.data.getId(),
                        tvShowEntityResource.data.getTitle(),
                        tvShowEntityResource.data.getReleaseDate(),
                        tvShowEntityResource.data.getGenre(),
                        tvShowEntityResource.data.getPosterPath(),
                        tvShowEntityResource.data.getRuntime(),
                        tvShowEntityResource.data.getOverview(),
                        tvShowEntityResource.data.isFavorite()
                ));
            }
        });
    }

    //TODO: BIKIN MUTABLElIVEDATA<FILMDETAIL> PUBLIC TRUS BIKIN SETTER. OBSERVE DI SETTERNYA
    public LiveData<FilmDetail> getDetailFilm(){
        return detailFilm;
    }

    public void setFavoriteMovie(boolean isFavorite){
        watchOnRepository.setFavoriteMovie(movieEntity, isFavorite);
    }

    public void setFavoriteTvShow(boolean isFavorite){
        watchOnRepository.setFavoriteTvShow(tvShowEntity, isFavorite);
    }
}
