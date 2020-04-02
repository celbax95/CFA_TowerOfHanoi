package com.example.towerofhanoi.init;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

@SuppressLint("Registered")
public class InitApplication extends Application {
    public static final String NIGHT_MODE = "NIGHT_MODE";
    private static InitApplication singleton = null;
    private boolean nightModeEnabled = false;
    private Context context;

    public InitApplication(Context c) {
        context = c;
    }

    public static InitApplication getInstance(Context c) {

        if (singleton == null) {
            singleton = new InitApplication(c);
            singleton.onCreate();
        }
        return singleton;
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
