package com.example.watchon.utils;

import com.example.watchon.data.source.local.entity.MovieEntity;
import com.example.watchon.data.source.local.entity.TvShowEntity;

import java.util.ArrayList;
import java.util.List;

public class DummyData {

    public static MovieEntity generateMovieEntity(){
        return new MovieEntity(
                1,
                "Tenet",
                "2020-08-22",
                "Romantic Comedy",
                "https://image.tmdb.org/t/p/w500//k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                150,
                "",
                true
        );
    }

    public static TvShowEntity generateTvShowEntity(){
        return new TvShowEntity(
                1,
                "Tenet",
                "2020-08-22",
                "Romantic Comedy",
                "https://image.tmdb.org/t/p/w500//k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                150,
                "",
                true
        );
    }

    public static List<MovieEntity> generateMovieEntities(){
        ArrayList<MovieEntity> movieEntities = new ArrayList<>();
        movieEntities.add(new MovieEntity(
                577922,
                "Tenet",
                "2020-08-22",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                120,
                "",
                true
        ));
        movieEntities.add(new MovieEntity(
                651571,
                "Breach",
                "2020-12-17",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//13B6onhL6FzSN2KaNeQeMML05pS.jpg",
                140,
                "",
                true
        ));
        movieEntities.add(new MovieEntity(
                651571,
                "Breach",
                "2020-12-17",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//13B6onhL6FzSN2KaNeQeMML05pS.jpg",
                140,
                "",
                true
        ));
        movieEntities.add(new MovieEntity(
                651571,
                "Breach",
                "2020-12-17",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//13B6onhL6FzSN2KaNeQeMML05pS.jpg",
                140,
                "",
                true
        ));
        movieEntities.add(new MovieEntity(
                651571,
                "Breach",
                "2020-12-17",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//13B6onhL6FzSN2KaNeQeMML05pS.jpg",
                140,
                "",
                true
        ));
        return movieEntities;
    }

    public static List<TvShowEntity> generateTvShowEntities(){
        ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();
        tvShowEntities.add(new TvShowEntity(
                577922,
                "Tenet",
                "2020-08-22",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                120,
                "",
                true
        ));
        tvShowEntities.add(new TvShowEntity(
                651571,
                "Breach",
                "2020-12-17",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//13B6onhL6FzSN2KaNeQeMML05pS.jpg",
                140,
                "",
                true
        ));
        tvShowEntities.add(new TvShowEntity(
                577922,
                "Tenet",
                "2020-08-22",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                120,
                "",
                true
        ));tvShowEntities.add(new TvShowEntity(
                577922,
                "Tenet",
                "2020-08-22",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                120,
                "",
                true
        ));
        tvShowEntities.add(new TvShowEntity(
                577922,
                "Tenet",
                "2020-08-22",
                "Romance Action Drama",
                "https://image.tmdb.org/t/p/w500//k68nPLbIST6NP96JmTxmZijEvCA.jpg",
                120,
                "",
                true
        ));
        return tvShowEntities;
    }
}
