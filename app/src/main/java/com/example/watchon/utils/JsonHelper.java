package com.example.watchon.utils;

import com.example.watchon.data.source.remote.response.movie.detailmovie.MovieGenresItem;
import com.example.watchon.data.source.remote.response.tvshow.tvshowdetail.TvShowGenresItem;

import java.util.List;

public class JsonHelper {

    public static String movieGenreListToString(List<MovieGenresItem> movieGenresItems){
        StringBuilder result = new StringBuilder();
        for (MovieGenresItem movieGenresItem : movieGenresItems) {
            result.append(" ").append(movieGenresItem.getName());
        }
        return result.toString();
    }

    public static String tvShowGenreListToString(List<TvShowGenresItem> tvShowGenresItems){
        StringBuilder result = new StringBuilder();
        for (TvShowGenresItem tvShowGenresItem : tvShowGenresItems) {
            result.append(" ").append(tvShowGenresItem.getName());
        }
        return result.toString();
    }
}
