package com.app.doctorsdoor.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.app.doctorsdoor.web.PDPlusApplication;

import java.util.Set;


/**
 * Created by Pulah on 12-09-2018 12:01 PM.
 */
public class LocalStorage {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static LocalStorage localStorage;

    private LocalStorage() {
        Context mContext = PDPlusApplication.getAppContext().getApplicationContext();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    private static synchronized LocalStorage getLocalStorage() {
        if (localStorage == null) {
            localStorage = new LocalStorage();
        }
        return localStorage;
    }

    public static void save(String key, int value) {
        getLocalStorage().editor.putInt(key, value);
        getLocalStorage().editor.commit();
    }

    public static void save(String key, float value) {
        getLocalStorage().editor.putFloat(key, value);
        getLocalStorage().editor.commit();
    }

    public static void save(String key, boolean value) {
        getLocalStorage().editor.putBoolean(key, value);
        getLocalStorage().editor.commit();
    }

    public static void save(String key, String value) {
        getLocalStorage().editor.putString(key, value);
        getLocalStorage().editor.commit();
    }

    public static void save(String key, long value) {
        getLocalStorage().editor.putLong(key, value);
        getLocalStorage().editor.commit();
    }

    public static void save(String key, Set<String> value) {
        getLocalStorage().editor.putStringSet(key, value);
        getLocalStorage().editor.commit();
    }


    public static int read(String key, int defaultValue) {
        return getLocalStorage().sharedPreferences.getInt(key, defaultValue);
    }

    public static boolean read(String key, boolean defaultValue) {
        return getLocalStorage().sharedPreferences.getBoolean(key, defaultValue);
    }

    public static float read(String key, float defaultValue) {
        return getLocalStorage().sharedPreferences.getFloat(key, defaultValue);
    }

    public static long read(String key, long defaultValue) {
        return getLocalStorage().sharedPreferences.getLong(key, defaultValue);
    }

    public static String read(String key, String defaultValue) {
        return getLocalStorage().sharedPreferences.getString(key, defaultValue);
    }

    public static Set<String> read(String key) {
        return getLocalStorage().sharedPreferences.getStringSet(key, null);
    }


    public static void clear(String key) {
        getLocalStorage().editor.remove(key);
        getLocalStorage().editor.commit();
    }

    public static void clear() {
        getLocalStorage().editor.clear();
        getLocalStorage().editor.commit();
    }
}