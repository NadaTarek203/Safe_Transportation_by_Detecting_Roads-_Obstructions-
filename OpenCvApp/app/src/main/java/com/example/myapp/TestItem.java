package com.example.myapp;

import com.google.gson.annotations.SerializedName;

public class TestItem{

	@SerializedName("ymin")
	private int ymin;

	@SerializedName("xmin")
	private int xmin;

	@SerializedName("scaled_xmin")
	private Object scaledXmin;

	@SerializedName("scaled_ymin")
	private Object scaledYmin;

	@SerializedName("ymax")
	private int ymax;

	@SerializedName("distance")
	private Object distance;

	@SerializedName("xmax")
	private int xmax;

	@SerializedName("name")
	private String name;

	@SerializedName("scaled_xmax")
	private Object scaledXmax;

	@SerializedName("scaled_ymax")
	private Object scaledYmax;

	public void setYmin(int ymin){
		this.ymin = ymin;
	}

	public int getYmin(){
		return ymin;
	}

	public void setXmin(int xmin){
		this.xmin = xmin;
	}

	public int getXmin(){
		return xmin;
	}

	public void setScaledXmin(Object scaledXmin){
		this.scaledXmin = scaledXmin;
	}

	public Object getScaledXmin(){
		return scaledXmin;
	}

	public void setScaledYmin(Object scaledYmin){
		this.scaledYmin = scaledYmin;
	}

	public Object getScaledYmin(){
		return scaledYmin;
	}

	public void setYmax(int ymax){
		this.ymax = ymax;
	}

	public int getYmax(){
		return ymax;
	}

	public void setDistance(Object distance){
		this.distance = distance;
	}

	public Object getDistance(){
		return distance;
	}

	public void setXmax(int xmax){
		this.xmax = xmax;
	}

	public int getXmax(){
		return xmax;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setScaledXmax(Object scaledXmax){
		this.scaledXmax = scaledXmax;
	}

	public Object getScaledXmax(){
		return scaledXmax;
	}

	public void setScaledYmax(Object scaledYmax){
		this.scaledYmax = scaledYmax;
	}

	public Object getScaledYmax(){
		return scaledYmax;
	}
}