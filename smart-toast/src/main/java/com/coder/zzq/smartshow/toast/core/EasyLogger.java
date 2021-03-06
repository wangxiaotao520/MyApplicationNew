package com.coder.zzq.smartshow.toast.core;

import android.util.Log;

import com.coder.zzq.smartshow.toast.BuildConfig;

public class EasyLogger {
    public static final String LOG_TAG = "smart-show";

    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(LOG_TAG, msg);
        }
    }

    public static void e(String error) {
        if (BuildConfig.DEBUG) {
            Log.e(LOG_TAG, error);
        }
    }
}
