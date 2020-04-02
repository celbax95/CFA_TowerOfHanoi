package com.example.towerofhanoi.init;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Locale;

public class InitApplication extends Application {
    public static final String NIGHT_MODE = "NIGHT_MODE";

    private boolean nightModeEnabled = false;

    private static InitApplication singleton = null;

    private Context context;

    public static InitApplication getInstance(Context c) {

        if(singleton == null)
        {
            singleton = new InitApplication(c);
            singleton.onCreate();
        }
        return singleton;
    }

    public InitApplication(Context c) {
        context = c;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        singleton = this;
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.nightModeEnabled = mPrefs.getBoolean(NIGHT_MODE, false);
    }

    public boolean isNightModeEnabled() {
        return nightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean nightModeEnabled) {
        this.nightModeEnabled = nightModeEnabled;

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(NIGHT_MODE, this.nightModeEnabled);
        editor.apply();
    }
}
