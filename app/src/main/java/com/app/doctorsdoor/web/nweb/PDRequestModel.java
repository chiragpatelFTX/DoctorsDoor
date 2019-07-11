package com.app.doctorsdoor.web.nweb;

import android.content.Context;

/**
 * Created by Pulah Nandha on 21-02-2019.
 */
public class PDRequestModel {
    private String url;
    private Object params;
    private Context context;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getJsonObjectParam() {
        return params;
    }

    public void setJsonObjectParam(Object jsonObjectParam) {
        this.params = jsonObjectParam;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
