package com.example.towerofhanoi.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.prefs.PreferenceChangeEvent;

public class Settings {

    private static final String DISKS_NUMBER = "DISKS_NUMBER";

    private static final int MIN_DISKS_NUMBER = 3;

    private static Settings instance = null;

    private Context context;

    private int disksNumber;

    public void setDisksNumber(int disksNumber) {
        this.disksNumber = disksNumber;

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(DISKS_NUMBER, this.disksNumber);
        editor.apply();
    }

    public int getDisksNumber() {
        return disksNumber;
    }

    private Settings(Context context) {
        this.context = context;

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int i = 0;
        disksNumber = pref.getInt(DISKS_NUMBER, MIN_DISKS_NUMBER);
    }

    public static Settings getInstance(Context context) {
        if (instance == null) {
            instance = new Settings(context);
        }

        return instance;
    }

    SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = mPrefs.edit();
}
