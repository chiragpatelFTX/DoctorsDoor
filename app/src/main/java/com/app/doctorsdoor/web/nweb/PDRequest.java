package com.app.doctorsdoor.web.nweb;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.doctorsdoor.web.PDPlusApplication;
import com.app.doctorsdoor.web.VolleySingleton;
import com.app.doctorsdoor.web.model.PDResponse;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pulah Nandha on 21-02-2019.
 * NOTE: This will be the only class which will use Volley
 * NO IMPLEMENTATION OF VOLLEY OUT OF THIS CLASS.
 */
class PDRequest {
    private static final String TAG = PDRequest.class.getName();
    private static final int ERROR_CODE_SERVER_NOT_REACHABLE = 0;

    PDRequest() {
    }

    public void post(PDRequestModel requestModel, RequestManager requestManager) {
        sendRequest(requestModel, requestManager, Request.Method.POST);
    }

    public void get(PDRequestModel requestModel, RequestManager requestManager) {
        sendRequest(requestModel, requestManager, Request.Method.GET);
    }

    public void put(PDRequestModel requestModel, RequestManager requestManager) {
        sendRequest(requestModel, requestManager, Request.Method.PUT);
    }

    public void delete(PDRequestModel requestModel, RequestManager requestManager) {
        sendRequest(requestModel, requestManager, Request.Method.DELETE);
    }


    private void sendRequest(PDRequestModel requestModel, final RequestManager requestManager, int method) {
        final String url = requestModel.getUrl();
        // FileLog.i(TAG, "PDRequest : URL : " + url);
        final Gson gson = new Gson();
        JSONObject jsonObjectParams = null;
        Object params = requestModel.getJsonObjectParam();
        if (params != null) {
            try {
                jsonObjectParams = new JSONObject(gson.toJson(requestModel.getJsonObjectParam()));
            } catch (JSONException e) {
                //FileLog.e(TAG, "JSONException : " + FileLog.getStackTraceString(e));
            }
        }
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                method, url, jsonObjectParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        PDResponse pdResponse = gson.fromJson(response.toString(), PDResponse.class);
                        requestManager.onResponseReceived(pdResponse);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // FileLog.e(TAG, "VolleyError : " + Log.getStackTraceString(error));
                NetworkResponse response = error.networkResponse;
                PDResponse pdResponse = new PDResponse();

                //todo @pulah define enums for all error code.
                if (response != null) {
                    pdResponse.setCode(response.statusCode);
                    switch (response.statusCode) {
                        case 401:
                            pdResponse.setMessage("Unauthorized");
                            break;
                        case 400:
                            pdResponse.setMessage("Bad Request");
                            break;
                        case 404:
                            pdResponse.setMessage("Not Found");
                            break;
                        case 500:
                            pdResponse.setMessage("Internal Server Error");
                            break;
                        default:
                            pdResponse.setMessage("server_conn_issue");
                            break;
                    }
                } else {
                    pdResponse.setCode(ERROR_CODE_SERVER_NOT_REACHABLE);
                    pdResponse.setMessage("server_not_reachable");
                }
                requestManager.onResponseReceived(pdResponse);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                //String token = LocalStorage.read(Constants.storage.TOKEN, null);
                HashMap<String, String> headers = new HashMap<String, String>();
               /* if (token != null) {//only in login request this will be false.
                    headers.put(TagField.TAG_Authorization, token);
                    headers.put(TagField.TAG_Content_Type, TagFieldValue.TAG_CONTENT_TYPE_VALUE_JSON);
                    return headers;
                }*/
                return super.getHeaders();
            }


            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                //here I am assuming that I will always receive auth token.
              /*  String authToken = response.headers.get(TagField.TAG_Authorization).trim();
                LocalStorage.save(Constants.storage.TOKEN, authToken);*/
                return super.parseNetworkResponse(response);
            }
        };
        // Adding request to request queue
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                6000 * 10,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        jsonObjReq.setShouldCache(false);
        Context context = PDPlusApplication.getAppContext().getApplicationContext();
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjReq);
    }
}
