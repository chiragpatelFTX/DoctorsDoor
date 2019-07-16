package com.app.doctorsdoor.rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.app.doctorsdoor.R;
import com.app.doctorsdoor.activity.DoctorRegistrationActivity;
import com.app.doctorsdoor.activity.MainActivity;
import com.app.doctorsdoor.common.CustomToast;
import com.app.doctorsdoor.common.ProgressDialogCustom;
import com.app.doctorsdoor.storage.Constants;
import com.app.doctorsdoor.storage.LocalStorage;
import com.app.doctorsdoor.web.RequestManager;
import com.app.doctorsdoor.web.WebResponsible;
import com.app.doctorsdoor.web.exception.PDException;
import com.app.doctorsdoor.web.model.PDResponse;
import com.app.doctorsdoor.web.model.PDWebRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Damini Mehra on 30-06-2019 at 18:21.
 */
public class LoginRequest implements WebResponsible {
    private final int LOGIN_REQUEST = 302;
    private Context context;
    String json, loginType;

    public void login(String json, Context context, String loginType) {
        this.json = json;
        this.context = context;
        this.loginType = loginType;
        ProgressDialogCustom.pDialog = new ProgressDialog(context);
        ProgressDialogCustom.pDialog.setMessage(context.getString(R.string.loading));
        ProgressDialogCustom.pDialog.setCanceledOnTouchOutside(false);
        ProgressDialogCustom.pDialog.show();
        PDWebRequest pdWebRequest = new PDWebRequest();
        pdWebRequest.setUrl(ApiClient.BASE_URL + "login");
        pdWebRequest.setRequestCode(LOGIN_REQUEST);
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

        if (requestCode == LOGIN_REQUEST && !data.getType().equalsIgnoreCase("Error")) {
            Log.e("Login: ", data.getMessage());
            if (ProgressDialogCustom.pDialog != null) {
                ProgressDialogCustom.pDialog.dismiss();
            }
            CustomToast.SingleToastShortContext(context, "Login Successfully");
            try {

                JSONObject jsonObject = new JSONObject(data.getData());
                JSONObject jsonPassword = new JSONObject(json);
                LocalStorage.save(Constants.storage.USER_NAME, jsonObject.getString(Constants.storage.USER_NAME));
                LocalStorage.save(Constants.storage.PASSWORD, jsonPassword.getString(Constants.storage.PASSWORD));
                LocalStorage.save(Constants.storage.USER_ID, jsonObject.getString(Constants.storage.USER_ID));
                LocalStorage.save(Constants.storage.USER_JSON, data.getData());
                if (loginType.equalsIgnoreCase("login")) {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, DoctorRegistrationActivity.class);
                    context.startActivity(intent);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            if (ProgressDialogCustom.pDialog != null) {
                ProgressDialogCustom.pDialog.dismiss();
            }
            CustomToast.SingleToastShortContext(context, data.getMessage());
        }
    }
}
