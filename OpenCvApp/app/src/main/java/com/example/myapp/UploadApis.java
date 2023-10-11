package com.example.myapp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UploadApis {
    @Multipart
    @POST("/im_size")
    Call<Response> uploadFrame(@Part("image") String image);//Call Replace by Single<ResponseBody>
}
