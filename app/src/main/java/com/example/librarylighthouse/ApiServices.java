package com.example.librarylighthouse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
// Potentially other imports if User class is in a different package

public interface ApiServices {
    @POST("/api/register")
    Call<ResponseBody> registerUser(
            @Body User user
    );
    @POST("/api/login")
    Call<ResponseBody> loginUser(
            @Body User user
    );
    @GET("/api/user")
    Call<ResponseBody> getUser(
            @Header("Authorization") String token
    );
    @GET("/api/logout")
    Call<ResponseBody> logoutUser(
            @Header("Authorization") String token
    );

}