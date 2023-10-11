//package com.example.myapp;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class WeatherData {
//    private static final String BASE_URL = "https://api.weatherapi.com/v1/";
//    private static final String API_KEY = "a328ff3112ad4166af2ba84eff4e6370";
//
//    private WeatherAPIServices service;
//
//    public WeatherData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        service = retrofit.create(WeatherAPIServices.class);
//    }
//
//    Call<weatherResponse> call = service.getWeatherByCoordinates(latitude, longitude, apiKey);
//
//
//}
