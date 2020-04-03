package com.example.towerofhanoi.fragment;

import android.content.Context;

import com.example.towerofhanoi.app.FragmentManager;

public abstract class Fragment extends androidx.fragment.app.Fragment {

    protected final String name;
    protected Context context;
    FragmentManager fragmentManager;

    public Fragment(Context context, FragmentManager fragmentManager, String name) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean onBackPressed() {
        return true;
    }
}
