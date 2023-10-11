package com.example.myapp;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("sunrise")
	private String sunrise;

	@SerializedName("pod")
	private String pod;

	@SerializedName("pres")
	private Object pres;

	@SerializedName("sources")
	private List<String> sources;

	@SerializedName("ob_time")
	private String obTime;

	@SerializedName("timezone")
	private String timezone;

	@SerializedName("wind_cdir")
	private String windCdir;

	@SerializedName("lon")
	private Object lon;

	@SerializedName("clouds")
	private int clouds;

	@SerializedName("wind_spd")
	private Object windSpd;

	@SerializedName("city_name")
	private String cityName;

	@SerializedName("datetime")
	private String datetime;

	@SerializedName("h_angle")
	private int hAngle;

	@SerializedName("precip")
	private int precip;

	@SerializedName("station")
	private String station;

	@SerializedName("weather")
	private Weather weather;

	@SerializedName("elev_angle")
	private Object elevAngle;

	@SerializedName("dni")
	private Object dni;

	@SerializedName("lat")
	private Object lat;

	@SerializedName("uv")
	private Object uv;

	@SerializedName("vis")
	private int vis;

	@SerializedName("temp")
	private Object temp;

	@SerializedName("dhi")
	private Object dhi;

	@SerializedName("app_temp")
	private Object appTemp;

	@SerializedName("ghi")
	private Object ghi;

	@SerializedName("dewpt")
	private Object dewpt;

	@SerializedName("wind_dir")
	private int windDir;

	@SerializedName("solar_rad")
	private Object solarRad;

	@SerializedName("country_code")
	private String countryCode;

	@SerializedName("rh")
	private int rh;

	@SerializedName("slp")
	private Object slp;

	@SerializedName("snow")
	private int snow;

	@SerializedName("sunset")
	private String sunset;

	@SerializedName("aqi")
	private int aqi;

	@SerializedName("state_code")
	private String stateCode;

	@SerializedName("wind_cdir_full")
	private String windCdirFull;

	@SerializedName("gust")
	private Object gust;

	@SerializedName("ts")
	private int ts;

	public String getSunrise(){
		return sunrise;
	}

	public String getPod(){
		return pod;
	}

	public Object getPres(){
		return pres;
	}

	public List<String> getSources(){
		return sources;
	}

	public String getObTime(){
		return obTime;
	}

	public String getTimezone(){
		return timezone;
	}

	public String getWindCdir(){
		return windCdir;
	}

	public Object getLon(){
		return lon;
	}

	public int getClouds(){
		return clouds;
	}

	public Object getWindSpd(){
		return windSpd;
	}

	public String getCityName(){
		return cityName;
	}

	public String getDatetime(){
		return datetime;
	}

	public int getHAngle(){
		return hAngle;
	}

	public int getPrecip(){
		return precip;
	}

	public String getStation(){
		return station;
	}

	public Weather getWeather(){
		return weather;
	}

	public Object getElevAngle(){
		return elevAngle;
	}

	public Object getDni(){
		return dni;
	}

	public Object getLat(){
		return lat;
	}

	public Object getUv(){
		return uv;
	}

	public int getVis(){
		return vis;
	}

	public Object getTemp(){
		return temp;
	}

	public Object getDhi(){
		return dhi;
	}

	public Object getAppTemp(){
		return appTemp;
	}

	public Object getGhi(){
		return ghi;
	}

	public Object getDewpt(){
		return dewpt;
	}

	public int getWindDir(){
		return windDir;
	}

	public Object getSolarRad(){
		return solarRad;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public int getRh(){
		return rh;
	}

	public Object getSlp(){
		return slp;
	}

	public int getSnow(){
		return snow;
	}

	public String getSunset(){
		return sunset;
	}

	public int getAqi(){
		return aqi;
	}

	public String getStateCode(){
		return stateCode;
	}

	public String getWindCdirFull(){
		return windCdirFull;
	}

	public Object getGust(){
		return gust;
	}

	public int getTs(){
		return ts;
	}
}