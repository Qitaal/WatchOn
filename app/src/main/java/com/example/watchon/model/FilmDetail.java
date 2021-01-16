package com.example.watchon.model;

public class FilmDetail {
    private int id;
    private String title;
    private String releaseDate;
    private String genre;
    private String posterPath;
    private long runtime;
    private String overview;
    private boolean favorite;

    public FilmDetail(int id, String title, String releaseDate, String genre, String posterPath, long runtime, String overview, boolean favorite) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.posterPath = posterPath;
        this.runtime = runtime;
        this.overview = overview;
        this.favorite = favorite;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public long getRuntime() {
        return runtime;
    }

    public String getOverview() {
        return overview;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
