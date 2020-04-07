package com.example.towerofhanoi.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.towerofhanoi.model.Score;

import java.util.ArrayList;

public class ScoresRepository {
    private static final String TABLE = "SCORES";

    private static ScoresRepository instance;

    static {
        instance = null;
    }

    private DataBaseManager dbm;

    private ScoresRepository(Context context) {
        dbm = DataBaseManager.getInstance(context);
    }

    public static ScoresRepository getInstance(Context context) {
        if (instance == null) {
            instance = new ScoresRepository(context);
        }
        return instance;
    }

    public boolean add(Score s) {
        ContentValues values = new ContentValues();
        values.put("date", s.getDate());
        values.put("disks", s.getDisks());
        values.put("moves", s.getMoves());
        values.put("time", s.getTime());

        long line = dbm.getWritableDatabase().insert(TABLE, null, values);

        return line != 0;
    }

    public boolean remove(Score s) {
        String[] identifier = {String.valueOf(s.getDate()), String.valueOf(s.getDisks())};

        long line = dbm.getWritableDatabase().delete(TABLE, "date=? and disks=?", identifier);

        return line != 0;
    }

    public ArrayList<Score> getOrderedByDate(int disks) {
        return get(disks, "date, moves, time");
    }

    public ArrayList<Score> getOrderedByTime(int disks) {
        return get(disks, "time, moves, date");
    }

    public ArrayList<Score> getOrderedByMoves(int disks) {
        return get(disks, "moves, time, date");
    }

    public ArrayList<Score> get(int disks, String orderBy) {
        ArrayList<Score> scores = new ArrayList<>();

        Cursor c = dbm.getReadableDatabase().rawQuery(
                "SELECT date as date, disks as disks, moves as moves, time as time " +
                        "FROM " + TABLE + " " +
                        "WHERE disks = ? " +
                        "ORDER BY " + orderBy,
                new String[] {String.valueOf(disks)});

        if (c.getCount() == 0) {
            return scores;
        }

        c.moveToFirst();
        while (!c.isAfterLast()) {
            int i = 0;

            Score s = new Score();
            s.setDate(c.getLong(c.getColumnIndex("date")));
            s.setDisks(c.getInt(c.getColumnIndex("disks")));
            s.setMoves(c.getInt(c.getColumnIndex("moves")));
            s.setTime(c.getInt(c.getColumnIndex("time")));

            scores.add(s);
            c.moveToNext();
        }

        c.close();

        return scores;
    }

    public boolean removeAll() {
        long line = dbm.getWritableDatabase().delete(TABLE, null, null);
        return line != 0;
    }

}
