package com.example.myapp;

import androidx.annotation.NonNull;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.CameraActivity;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends CameraActivity implements LocationListener {
    CameraBridgeViewBase cameraBridgeViewBase;
    private static final String API_KEY="a328ff3112ad4166af2ba84eff4e6370";
    Mat mRGBA, mRGBAT;
    ImageView mBitmap = null;
    Bitmap bitmap = null;
    float speed;
    double lon,lat;
    private TextToSpeech textToSpeech;
    private UploadApis mApi;
    private static final int PERMISSIONS_REQUEST_LOCATION = 100;
    private static final long MIN_TIME_BETWEEN_UPDATES = 5000;
    private static final float MIN_DISTANCE_CHANGE_FOR_UPDATES = 5;
    LocationManager locationManager;
    TextView Addresstext;
    TextView Speedtxt,Temp,State;
    double BrakingDistance;
    Button stopbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermession();
        //
        Temp=findViewById(R.id.temp);
        State=findViewById(R.id.state);

        stopbtn=findViewById(R.id.stop);
        stopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,StartRide.class);
                startActivity(intent);
            }
        });
        Addresstext=findViewById(R.id.textView);
        Speedtxt=findViewById(R.id.speed);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        }
        else{
            getLocation();
        }

        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status==TextToSpeech.SUCCESS)
                {
                    int res=textToSpeech.setLanguage(Locale.ENGLISH);
                    if(res==TextToSpeech.LANG_MISSING_DATA || res==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","lang not supported");

                    }

                }
                else
                {
                    Log.e("TTS","Initailzation failed");

                }
            }
        });

        /*OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();*/
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String s) {
                Log.e("OkHttp",s);
            }
        });
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://7e90-102-41-172-78.ngrok-free.app") // Replace with your server URL
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Retrofit WeatherRetro = new Retrofit.Builder()
                .baseUrl("https://api.weatherbit.io/v2.0/") // Replace with your server URL
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherAPIServices weatherApiService = WeatherRetro.create(WeatherAPIServices.class);
        //Toast.makeText(this, "In getLocation", Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, "" + lat + "," + lon, Toast.LENGTH_SHORT).show();
        if(lat!=0.0&&lon!=0.0){
            Call<WeatherResponse> call= weatherApiService.getWeatherByCoordinates(lat,lon,"local","a328ff3112ad4166af2ba84eff4e6370");

            call.enqueue(new Callback<WeatherResponse>() {

                @Override
                public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                    if(response.isSuccessful()) {
                        //Toast.makeText(MainActivity.this, "wwwwoooooooooooorrrrrrrkkkkkkkkk", Toast.LENGTH_SHORT).show();
                        WeatherResponse weatherData = response.body();
                        for (int i = 0; i < response.body().getData().size(); i++) //25r t3del zwdt el for loop
                        {

                            String Temperature= weatherData.getData().get(i).getTemp()+"C";
                            String TempState=weatherData.getData().get(i).getWeather().getDescription();
                            String city=weatherData.getData().get(i).getCityName();
                            Toast.makeText(MainActivity.this, "Temperature: " + weatherData.getData().get(i).getTemp()  + " C", Toast.LENGTH_SHORT).show();
                            Temp.setText(Temperature);
                            State.setText(TempState);
                            Addresstext.setText(city);
                        }
                    }
                    else{
                        System.out.println("WEATHER NOT WOOOORK");
                        Toast.makeText(MainActivity.this, "Notwork", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<WeatherResponse> call, Throwable t) {
                    System.out.println("REQUEST NOT WOOOORK");
                }
            });
        }

        // Create an instance of the API interface using the Retrofit client
        mApi = retrofit.create(UploadApis.class);
        cameraBridgeViewBase=findViewById(R.id.cameraView);
        cameraBridgeViewBase.setCvCameraViewListener(new CameraBridgeViewBase.CvCameraViewListener2() {
            @Override
            public void onCameraViewStarted(int width, int height) {
                mRGBA = new Mat(height, width, CvType.CV_8UC4);
//                Bitmap x=captureBitmap(mRGBA);
//                String base64Frame=ConvertBitmapToBase64(x);
//                System.out.println("yyyyyyyyyyyyyyyyy"+base64Frame.toString());
            }

            @Override
            public void onCameraViewStopped() {

            }

            @Override
            public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
                Mat frame = inputFrame.rgba();

                // Convert the frame to a Bitmap
                Bitmap bitmap = Bitmap.createBitmap(frame.cols(), frame.rows(), Bitmap.Config.ARGB_8888);
                Utils.matToBitmap(frame, bitmap);

                // Convert the Bitmap to a Base64-encoded string
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] bytes = baos.toByteArray();
                //Problem: Respose has white spaces
                //Answer: we use Base64.NO_WRAP instead of Base64.DEFAULT
                String base64 = "data:image/png;base64," + Base64.encodeToString(bytes, Base64.NO_WRAP);
                //String base64 = Base64.encodeToString(bytes, Base64.NO_WRAP);
                //base64.trim();
                Log.e("CONVERT IMAGE : ", "" + base64);
                //OkHttpClient client = new OkHttpClient();
                // Create a request body containing the Base64-encoded frame
                RequestBody body = RequestBody.create(MediaType.parse("text/plain"), base64);
                // Create a MultipartBody.Part containing the Base64-encoded frame
                MultipartBody.Part framePart = MultipartBody.Part.createFormData("image", "image.png", body);
                MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addPart(framePart);
                RequestBody requestBody2 = requestBodyBuilder.build();
                System.out.println(requestBody2);
                // Send the frame to the server using the Retrofit client
                /*JSONObject paramObject = new JSONObject();
                try {
                    paramObject.put("image", "data:image/png;base64,"+base64);
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                //paramObject.put("userName", name);
                Call<com.example.myapp.Response> call = mApi.uploadFrame(base64);
                call.enqueue(new Callback<com.example.myapp.Response>() {
                    @Override
                    public void onResponse(Call<com.example.myapp.Response> call, Response<com.example.myapp.Response> response) {
                        if(response.isSuccessful())
                        {
                            for (int i =0;i<response.body().getTest().size();i++) //25r t3del zwdt el for loop
                            {
                                String ObjName=response.body().getTest().get(i).getName();//kant get(0)
                                double Distance= (double) response.body().getTest().get(i).getDistance();
                                Distance=Math.round(Distance);
                                System.out.println(response.body().getTest());
                                System.out.println("objName "+ObjName);

                                double BrakingDistance=(5*5)/2*9.81;
                                BrakingDistance=Math.round(BrakingDistance);
                                speak("there is a "+ObjName+"  at " +BrakingDistance+"Meter");

                            }

                        }
                        else{
                            System.out.println("noooooooooooooooooooot successful");
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.myapp.Response> call, Throwable t) {
                        System.out.println("noooooooooooooooooooot work");
                        System.out.println(t.getMessage());


                    }
                });
                return frame;
            }
            private Bitmap captureBitmap(Mat frame1){
                //bitmap = Bitmap.createBitmap(cameraBridgeViewBase.getWidth()/4,cameraBridgeViewBase.getHeight()/4, Bitmap.Config.ARGB_8888);
                Mat rgba=new Mat();
                Imgproc.cvtColor(frame1,rgba,Imgproc.COLOR_BGR2RGB);
                try {
                    bitmap = Bitmap.createBitmap(mRGBA.cols(), mRGBA.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(rgba, bitmap);
                    Log.i("passed","success");
                    //mBitmap.setImageBitmap(bitmap);
                    //mBitmap.invalidate();
                    //System.out.println("zzzzzzzzzzzzzzz"+bitmap.toString());
                }catch(Exception ex){
                    Log.d("Exception",ex.getMessage());
                }
                return bitmap;
            }

            private String ConvertBitmapToBase64(Bitmap bitmap){
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); // bm is the bitmap object
                byte[] b = baos.toByteArray();
                return Base64.encodeToString(b, Base64.DEFAULT);
            }

        });

        if(OpenCVLoader.initDebug())
        {
            cameraBridgeViewBase.enableView();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        cameraBridgeViewBase.enableView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraBridgeViewBase.disableView();



    }

    @Override
    protected List<? extends CameraBridgeViewBase> getCameraViewList() {
        return Collections.singletonList(cameraBridgeViewBase);
    }

    void getPermession(){
        if(checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA},101);
        }
           }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0 && grantResults[0]!=PackageManager.PERMISSION_GRANTED)
        {
            getPermession();
        }

    }
    private void speak(String text){
        // String text=editText.getText().toString();

        textToSpeech.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }


    @Override
    protected void onDestroy() {
        cameraBridgeViewBase.disableView();
        if(textToSpeech!=null)
        {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }



@SuppressLint("MissingPermission")
private void getLocation()
{
    try {

        locationManager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_BETWEEN_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }
    catch (Exception e){
        e.printStackTrace();
    }

}
    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, "" + location.getLatitude() + "," + location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);
                String addressString = address.getAddressLine(0);
              //  Addresstext.setText(addressString);
            } else {
                Addresstext.setText("Address not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Geocooooooder Error" + e.getMessage());
            Log.e("Geocooooooder Error", e.getMessage());
            Toast.makeText(MainActivity.this, "Geocoder Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        lat=location.getLatitude();
        lon=location.getLongitude();
        speed = location.getSpeed();
        Toast.makeText(this, "Speed: " + speed + " m/s", Toast.LENGTH_SHORT).show();
        Log.e("Location Speed", "Speed: " + speed + " m/s");
        speed=Math.round(speed);
        Speedtxt.setText(speed+" m/s");

    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        Toast.makeText(this, "GPS provider enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        Toast.makeText(this, "GPS provider disabled", Toast.LENGTH_SHORT).show();
    }
}