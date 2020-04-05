package com.example.towerofhanoi.model;

import android.annotation.SuppressLint;
import android.view.animation.ScaleAnimation;

import java.util.Calendar;
import java.util.Date;

public class Score {
    private static final double GET_H_FROM_MS = 3600000D;
    private static final double GET_M_FROM_MS = 60000D;
    private static final double GET_S_FROM_MS = 1000D;
    private static final double MS_PRECISION = 100D;

    private static final int UNDEFINED = -1;

    private long date;
    private int disks;
    private int moves;
    private long time;

    public Score() {
        date = System.currentTimeMillis();
        disks = UNDEFINED;
        moves = UNDEFINED;
        time = UNDEFINED;
    }

    @SuppressLint("DefaultLocale")
    public String getDateString() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        int mHour = calendar.get(Calendar.HOUR);
        int mMinutes = calendar.get(Calendar.MINUTE);
        int mSeconds = calendar.get(Calendar.SECOND);

        return String.format("%d:%d:%d\n%d/%d/%d", mHour, mMinutes, mSeconds, mDay, mMonth, mYear);
    }

    public Score(long date, int disks, int moves, long time) {
        this.date = date;
        this.disks = disks;
        this.moves = moves;
        this.time = time;
    }

    public String getTimeString() {
        long tmpTime = time;

        int h = (int) Math.floor(tmpTime/GET_H_FROM_MS);
        tmpTime%=GET_H_FROM_MS;
        int m = (int) Math.floor(tmpTime/GET_M_FROM_MS);
        tmpTime%=GET_M_FROM_MS;
        int s = (int) Math.floor(tmpTime/GET_S_FROM_MS);
        tmpTime%=GET_S_FROM_MS;
        int ms = (int) Math.round(tmpTime/MS_PRECISION);

        return (h==0?"00":h) + ":" + (m==0?"00":m) + ":" + (s==0?"00":s) + ":" + (ms==0?"00":ms);
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getDisks() {
        return disks;
    }

    public void setDisks(int disks) {
        this.disks = disks;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
