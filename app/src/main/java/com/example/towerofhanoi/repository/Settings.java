package com.example.towerofhanoi.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Settings {

    public static final int MAX_DISK_COUNT = 23;

    private static final String DISKS_NUMBER = "DISKS_NUMBER";

    public static final int MIN_DISK_COUNT = 3;

    private static Settings instance = null;

    private Context context;

    private int disksNumber;

    public void setDiskCount(int disksNumber) {
        this.disksNumber = disksNumber;

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putInt(DISKS_NUMBER, this.disksNumber);
        editor.apply();
    }

    public int getDiskCount() {
        return disksNumber;
    }

    private Settings(Context context) {
        this.context = context;

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int i = 0;
        disksNumber = pref.getInt(DISKS_NUMBER, MIN_DISK_COUNT);
    }

    public static Settings getInstance(Context context) {
        if (instance == null) {
            instance = new Settings(context);
        }

        return instance;
    }
}
