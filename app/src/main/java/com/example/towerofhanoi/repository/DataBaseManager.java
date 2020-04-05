package com.example.towerofhanoi.repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseManager extends SQLiteOpenHelper {

    private static String DATA_BASE_NAME = "towerOfHanoiDataBase.sqlite";
    private static int CURRENT_DB_VERSION = 1;

    private static DataBaseManager instance;

    static {
        instance = null;
    }

    private DataBaseManager(Context context) {
        super(context, DATA_BASE_NAME, null, CURRENT_DB_VERSION);
    }

    public static DataBaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseManager(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORES (" +
                "date BIGINT," +
                "disks INTEGER," +
                "moves INTEGER," +
                "time BIGINT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                // 1 -> 2
            case 2:
                // 2 -> 3
            case 3:
                // 3 -> 4
        }
    }
}
