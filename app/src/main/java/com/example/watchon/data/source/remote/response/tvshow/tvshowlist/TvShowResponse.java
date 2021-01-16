package com.example.watchon.data.source.remote.response.tvshow.tvshowlist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TvShowResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<ResultsItemTvShow> results;

	@SerializedName("total_results")
	private int totalResults;

	public TvShowResponse(int page, int totalPages, List<ResultsItemTvShow> results, int totalResults) {
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

	public List<ResultsItemTvShow> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}