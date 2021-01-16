package com.example.watchon.data.source.remote.response.tvshow.tvshowdetail;

import com.google.gson.annotations.SerializedName;

public class TvShowGenresItem {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public TvShowGenresItem(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}
}