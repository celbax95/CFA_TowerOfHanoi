package com.example.towerofhanoi.repository;

import android.content.Context;

public class Options {

    private Options instance;

    private Context context;

    private Options(Context context) {
        this.context = context;
    }

    public static Options getInstance(Context context) {
        this(context);

        
    }
}
