//package com.example.myapp;
//
//import android.content.BroadcastReceiver;
//import android.content.ComponentName;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.IntentSender;
//import android.content.ServiceConnection;
//import android.content.SharedPreferences;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageManager;
//import android.content.res.AssetManager;
//import android.content.res.Configuration;
//import android.content.res.Resources;
//import android.database.DatabaseErrorHandler;
//import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Bitmap;
//import android.graphics.drawable.Drawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.UserHandle;
//import android.view.Display;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.gson.JsonObject;
//
//import org.json.JSONObject;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//
//public abstract class WeatherApi extends Context {
//    private static final String BASE_URL = "https://api.weatherbit.io/v2.0/current";
//    private static final String API_KEY="a328ff3112ad4166af2ba84eff4e6370";
//
//
//
//    public interface WeatherListener{
//        void onWeatherLoaded(WeatherData data);
//        void onWeatherError(String message);
//    }
//
//   public void getCurrentWeather(double latitude, double longitude, WeatherListener listener){
//        String url=BASE_URL+"?lat="+latitude+"&lon="+longitude+"&key="+API_KEY;
//        RequestQueue queue= Volley.newRequestQueue(this);
//       JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET,url,null,response -> {
//           try {
//               JSONObject data = response.getJSONObject("data").getJSONObject(0);
//               String city = data.getJSONObject("city_name").toString();
//               String country = data.getJSONObject("country_code").toString();
//               String description = data.getJSONObject("weather").getJSONObject("description").toString();
//               double temperature = data.getJSONObject("temp").getDouble("celsius");
//           }
//           catch (Exception e){
//
//           }
//       })
//   }
//}
