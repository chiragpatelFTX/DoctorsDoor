package com.app.doctorsdoor.web.model;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.app.doctorsdoor.web.WebResponsible;


import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Pulah on 06-09-2018 9:54 AM.
 */
public class PDWebRequest {
    //Non-Zero integer for managing request code
    private int requestCode;
    //Complete url
    private String url;
    //not for json request
    private HashMap params;
    private JSONObject jsonParams;
    private WebResponsible responseHandler;
    private Request.Priority priority;
    private DefaultRetryPolicy retryPolicy;

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HashMap getParams() {
        return params;
    }

    public void setParams(HashMap params) {
        this.params = params;
    }

    public JSONObject getJsonParams() {
        return jsonParams;
    }

    public void setJsonParams(JSONObject jsonParams) {
        this.jsonParams = jsonParams;
    }


    public Request.Priority getPriority() {
        return priority;
    }

    public void setPriority(Request.Priority priority) {
        this.priority = priority;
    }

    public DefaultRetryPolicy getRetryPolicy() {
        return retryPolicy;
    }

    public void setRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
        this.retryPolicy = new DefaultRetryPolicy(initialTimeoutMs, maxNumRetries, backoffMultiplier);
    }
}
