package com.app.doctorsdoor.web;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.doctorsdoor.web.model.PDWebRequest;


import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pulah on 06-09-2018 11:51 AM.
 */
class PDJsonWebRequest extends JsonObjectRequest {

    PDJsonWebRequest(int method, PDWebRequest request, ResponseHandler responseHandler) {
        super(method, request.getUrl(), request.getJsonParams(), responseHandler, responseHandler);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        // String token = response.headers.get(TagField.TAG_Authorization);
       /* if (token != null) {
            token = token.trim();
            //todo @pulah Encript token for better security.
            //LocalStorage.save(Constants.storage.TOKEN, token);
        }*/
        return super.parseNetworkResponse(response);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        HashMap<String, String> headers = new HashMap<>();
        /*String token = LocalStorage.read(Constants.storage.TOKEN, null);
        if (token != null) {

            headers.put(TagField.TAG_Content_Type, TagFieldValue.TAG_CONTENT_TYPE_VALUE_JSON);
            headers.put(TagField.TAG_Authorization, token);
            return headers;
        }*/
        return super.getHeaders();
    }

    @Override
    public Priority getPriority() {
        return super.getPriority();
    }
}