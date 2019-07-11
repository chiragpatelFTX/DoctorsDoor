package com.app.doctorsdoor.web.model;

/**
 * Created by Pulah on 06-09-2018 9:54 AM.
 */
public class PDResponse {
    private int code;
    //Here I have to keep data as a string because data can be from jsonObject or jsonArray
    private String data, type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}