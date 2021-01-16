package com.example.watchon.data.source.remote.response.movie.detailmovie;

import com.google.gson.annotations.SerializedName;

public class MovieGenresItem {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public MovieGenresItem(String name, int id) {
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