package com.app.doctorsdoor.web;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.app.doctorsdoor.web.model.PDResponse;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Pulah on 06-09-2018 10:24 AM.
 */
class ResponseHandler implements Response.Listener, Response.ErrorListener {
    private static final String TAG = ResponseHandler.class.getName();
    public final int RESULT_OK = 1;
    public final int RESULT_ERROR = -1;
    WebResponsible responsible;
    private int requestCode;

    public ResponseHandler(int requestCode, WebResponsible responsible) {
        this.requestCode = requestCode;
        this.responsible = responsible;
    }

    @Override
    public void onResponse(Object response) {
        int resultCode = RESULT_OK;
        Log.i(TAG, "@@@@@@@@@@@@@@@@@ : " + response.toString());
        JSONObject jsonResponse = (JSONObject) response;
        PDResponse pdResponse = new PDResponse();
        try {
            pdResponse.setType(jsonResponse.getString("type"));
            pdResponse.setMessage(jsonResponse.getString("message"));
            //TODO For temporary put this condition
            if (jsonResponse.has("result")) {
                if (jsonResponse.getString("result") != null) {
                    pdResponse.setData(jsonResponse.getString("result"));
                }
            }
        } catch (JSONException jex) {
            pdResponse.setMessage("Error while parsing json data received from server.");
            resultCode = RESULT_ERROR;
        }
        responsible.onWebResponseReceived(requestCode, resultCode, pdResponse);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        PDResponse pdResponse = new PDResponse();
        NetworkResponse response = error.networkResponse;

        JSONObject jsonObject = new JSONObject();
        String message = "Unknown error occurred while sending url";
        try {
            if (response != null) {
                jsonObject.put("statusCode", response.statusCode);
                switch (response.statusCode) {
                    case 401:
                        message = "Message 1";
                        break;
                    case 400:
                        message = "Message 2";
                        break;
                    case 404:
                        message = "Message 3";
                        break;
                    case 500:
                        message = "Message 4";
                        break;
                    default:
                        message = "Message 5";
                        break;
                }
            } else {
                jsonObject.put("statusCode", -1);
                message = "Message 6";
            }
        } catch (JSONException jex) {
            // FileLog.e("ResponseHandler", "Exception while adding json : " + Log.getStackTraceString(jex));
        }

        Log.i(TAG, "%%%%%%%%%" + message);

//        pdResponse.setData(jsonObject);
        pdResponse.setMessage(message);
        responsible.onWebResponseReceived(requestCode, RESULT_ERROR, pdResponse);
    }
}