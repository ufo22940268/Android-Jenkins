package com.xinpinget.android_jenkins.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

/**
 * Created by cc on 2/16/16.
 */
public class JenkinsManager {


    public static final String SERVER_ADDR = "SERVER_ADDR";
    private final SharedPreferences mSharedPreferences;

    public JenkinsManager(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public String getServerAddr() {
        return mSharedPreferences.getString(SERVER_ADDR, null);
    }

    public void saveServerAddr(String addr) {
        mSharedPreferences.edit().putString(SERVER_ADDR, addr).apply();
    }
}
