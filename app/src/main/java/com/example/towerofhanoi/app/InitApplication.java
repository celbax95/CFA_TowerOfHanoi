package com.example.towerofhanoi.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class InitApplication extends Application {
    public static final String NIGHT_MODE = "NIGHT_MODE";
    public static final String LANGUAGE = "LANGUAGE";

    private String language = "en";//Locale.getDefault().toString();

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
        this.language = mPrefs.getString(LANGUAGE, "en");
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString(NIGHT_MODE, this.language);
        editor.apply();
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
