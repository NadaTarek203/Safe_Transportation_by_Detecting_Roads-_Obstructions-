package com.example.myapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherAPIServices {
    @GET("current")
    Call<WeatherResponse> getWeatherByCoordinates(@Query("lat") double latitude,
                                                  @Query("lon") double longitude,
                                                  @Query("tz") String timezone,
                                                  @Query("key") String apiKey);
}
