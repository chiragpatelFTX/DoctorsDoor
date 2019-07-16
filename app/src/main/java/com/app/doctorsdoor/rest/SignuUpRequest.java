package com.app.doctorsdoor.rest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.app.doctorsdoor.activity.DoctorRegistrationActivity;
import com.app.doctorsdoor.activity.MainActivity;
import com.app.doctorsdoor.common.CustomToast;
import com.app.doctorsdoor.storage.Constants;
import com.app.doctorsdoor.web.RequestManager;
import com.app.doctorsdoor.web.WebResponsible;
import com.app.doctorsdoor.web.exception.PDException;
import com.app.doctorsdoor.web.model.PDResponse;
import com.app.doctorsdoor.web.model.PDWebRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Damini Mehra on 27-06-2019 at 11:48.a
 */
public class SignuUpRequest implements WebResponsible {
    private final int GET_BILLING_ITEM_REQUEST_CODE = 301;
    private final int POST_DOCTOR_REGISTRATION = 303;
    private String json;
    private Context context;

    public void signup(String json, Context context) {
        this.json = json;
        this.context = context;
        PDWebRequest pdWebRequest = new PDWebRequest();
        pdWebRequest.setUrl(ApiClient.BASE_URL + "signup");
        pdWebRequest.setRequestCode(GET_BILLING_ITEM_REQUEST_CODE);
        try {
            pdWebRequest.setJsonParams(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestManager.jsonPostRequest(pdWebRequest, this);
        } catch (PDException e) {

        }


    }

    @Override
    public void onWebResponseReceived(int requestCode, int resultCode, PDResponse data) {
        Log.d("SignUpREsponse: ", data.getMessage());
        if (requestCode == GET_BILLING_ITEM_REQUEST_CODE && !data.getType().equalsIgnoreCase("Error")) {
            if (json != null) {
                try {
                    JSONObject signUpObject = new JSONObject(json);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(Constants.storage.USER_NAME, signUpObject.getString(Constants.storage.USER_NAME));
                    jsonObject.put(Constants.storage.PASSWORD, signUpObject.getString(Constants.storage.PASSWORD));
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.login(json, context, "signUP");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == POST_DOCTOR_REGISTRATION && !data.getType().equalsIgnoreCase("Error")) {

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);

        } else {
            CustomToast.SingleToastShortContext(context, data.getMessage());
        }

    }

    public void doctorRegister(String json, Context context) {

        this.json = json;
        this.context = context;
        PDWebRequest pdWebRequest = new PDWebRequest();
        pdWebRequest.setUrl(ApiClient.BASE_URL + "doctors");
        pdWebRequest.setRequestCode(POST_DOCTOR_REGISTRATION);
        try {
            pdWebRequest.setJsonParams(new JSONObject(json));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            RequestManager.jsonPostRequest(pdWebRequest, this);
        } catch (PDException e) {

        }
    }
}
