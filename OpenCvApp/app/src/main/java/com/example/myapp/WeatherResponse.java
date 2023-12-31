package com.example.myapp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class WeatherResponse {

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("count")
	private int count;

	public List<DataItem> getData(){
		return data;
	}

	public int getCount(){
		return count;
	}
}