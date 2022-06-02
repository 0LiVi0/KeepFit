package com.example.myapplication;


import com.example.myapplication.model.Login;
import com.example.myapplication.model.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostApi {


    String root = "http://52.158.46.50:8000/";
//    String root = "http://127.0.0.1:8000/";


    String base_local = root + "api/v1/";
    String BASE_URL = base_local + "account/";
    String POST_URL = base_local + "post/";
    String API_URL = root + "api/v1/";


    @POST("api-token-auth/")
    Call<User> login(@Body Login login);

    @POST("register/")
    Call<User> registrationUser(@Body User userModel);
}