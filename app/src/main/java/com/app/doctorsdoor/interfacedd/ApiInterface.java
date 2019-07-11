package com.app.doctorsdoor.interfacedd;

import com.app.doctorsdoor.model.User;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Damini Mehra on 26-06-2019 at 15:34.
 */
public interface ApiInterface {
    String ENDPOINT = "https://engineerstalk.in/doctorsdoor/";

    @Headers("Content-Type: application/json")
    @POST("signup")
    Call<JSONObject> getUser(@Body User user);
}
