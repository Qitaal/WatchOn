package com.example.watchon.data.source.local.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movie")
public class MovieEntity implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "genre")
    private String genre;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "runtime")
    private long runtime;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "favorite")
    private boolean favorite = false;

    public MovieEntity() {
    }

    public MovieEntity(int id, String title, String releaseDate, String genre, String posterPath, long runtime, String overview, Boolean favourite) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.posterPath = posterPath;
        this.runtime = runtime;
        this.overview = overview;
        if (favourite != null) this.favorite = favourite;
    }

    protected MovieEntity(Parcel in) {
        id = in.readInt();
        title = in.readString();
        releaseDate = in.readString();
        genre = in.readString();
        posterPath = in.readString();
        runtime = in.readLong();
        overview = in.readString();
        favorite = in.readByte() != 0;
    }

    public static final Creator<MovieEntity> CREATOR = new Creator<MovieEntity>() {
        @Override
        public MovieEntity createFromParcel(Parcel in) {
            return new MovieEntity(in);
        }

        @Override
        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(releaseDate);
        dest.writeString(genre);
        dest.writeString(posterPath);
        dest.writeLong(runtime);
        dest.writeString(overview);
        dest.writeByte((byte) (favorite ? 1 : 0));
    }
}
