package com.example.watchon.data.source.remote.response.tvshow.tvshowdetail;

import com.google.gson.annotations.SerializedName;

public class CreatedByItem{

	@SerializedName("gender")
	private int gender;

	@SerializedName("credit_id")
	private String creditId;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_path")
	private Object profilePath;

	@SerializedName("id")
	private int id;

	public int getGender(){
		return gender;
	}

	public String getCreditId(){
		return creditId;
	}

	public String getName(){
		return name;
	}

	public Object getProfilePath(){
		return profilePath;
	}

	public int getId(){
		return id;
	}
}