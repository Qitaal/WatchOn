package com.example.watchon.data.source.remote.response.tvshow.tvshowlist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResultsItemTvShow {

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("genre_ids")
	private List<Integer> genreIds;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("origin_country")
	private List<String> originCountry;

	@SerializedName("backdrop_path")
	private String backdropPath;

	@SerializedName("media_type")
	private String mediaType;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("original_name")
	private String originalName;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("vote_count")
	private int voteCount;

	public ResultsItemTvShow(int id, String name, String firstAirDate, String posterPath, String overview) {
		this.firstAirDate = firstAirDate;
		this.overview = overview;
		this.posterPath = posterPath;
		this.name = name;
		this.id = id;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOverview(){
		return overview;
	}

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public List<String> getOriginCountry(){
		return originCountry;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public String getMediaType(){
		return mediaType;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public String getOriginalName(){
		return originalName;
	}

	public double getPopularity(){
		return popularity;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public int getVoteCount(){
		return voteCount;
	}
}