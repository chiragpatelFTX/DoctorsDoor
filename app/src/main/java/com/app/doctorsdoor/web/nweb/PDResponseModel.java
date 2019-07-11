package com.app.doctorsdoor.web.nweb;

/**
 * Created by Pulah Nandha on 21-02-2019.
 */
public class PDResponseModel {
    private int responseCode;
    private String message;
    private String data;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
