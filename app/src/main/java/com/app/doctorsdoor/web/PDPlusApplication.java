package com.app.doctorsdoor.web;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.doctorsdoor.session.AppSession;


/*import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;*/

//import com.squareup.leakcanary.LeakCanary;

public class PDPlusApplication extends Application {
    private static final String TAG = PDPlusApplication.class.getSimpleName();
    private static PDPlusApplication mInstance;


    private RequestQueue mRequestQueue;
  /*  private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;*/

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        //  NetworkManager.getInstance(getApplicationContext());

        //Setting up Application level session.
        AppSession.createSession(getApplicationContext());

      /*  // Handle SSL handshake
        CommonClass.handleSSLHandshake();*/


    }

    public static Context getAppContext() {
        return mInstance.getApplicationContext();
    }

    public static synchronized PDPlusApplication getInstance() {
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /*  *//**
     * Gets the default {@link Tracker} for this {@link Application}.
     *
     * @return tracker
     *//*
    synchronized public Tracker getDefaultTracker() {
// To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }
        return sTracker;
    }*/

}