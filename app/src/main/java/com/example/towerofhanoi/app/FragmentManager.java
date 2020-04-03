package com.example.towerofhanoi.app;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public interface FragmentManager {
    public static final String MENU = "menu";
    public static final String SCORES = "scores";

    public void setFragment(String s);
    public List<String> getFragments();
    public void toggleNightMode();
}
