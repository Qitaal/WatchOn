package com.example.watchon.data.source.remote.response.movie.movielist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieResponse{
	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ResultsItemMovie> results;

	@SerializedName("total_results")
	private int totalResults;

	public MovieResponse(int page, int totalPages, List<ResultsItemMovie> results, int totalResults) {
		this.page = page;
		this.totalPages = totalPages;
		this.results = results;
		this.totalResults = totalResults;
	}

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<ResultsItemMovie> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}