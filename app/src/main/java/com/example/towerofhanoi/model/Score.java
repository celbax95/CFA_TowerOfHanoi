package com.example.towerofhanoi.model;

import android.annotation.SuppressLint;
import android.view.animation.ScaleAnimation;

import java.util.Calendar;
import java.util.Date;

public class Score {
    private static final double GET_H_FROM_S = 3600D;
    private static final double GET_M_FROM_S = 60D;

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

        return String.format("%02d:%02d:%02d\n%02d/%02d/%04d", mHour, mMinutes, mSeconds, mDay, mMonth, mYear);
    }

    public Score(long date, int disks, int moves, long time) {
        this.date = date;
        this.disks = disks;
        this.moves = moves;
        this.time = time;
    }

    @SuppressLint("DefaultLocale")
    public String getTimeString() {
        long tmpTime = time;

        String ret = "";
        boolean firstDone = false;

        int h = (int) Math.floor(tmpTime/ GET_H_FROM_S);
        if (h != 0) {
            ret+=(h+":");
            firstDone = true;
        }
        tmpTime%= GET_H_FROM_S;

        int m = (int) Math.floor(tmpTime/ GET_M_FROM_S);
        if (m != 0 || firstDone) {
            ret = String.format(ret+"%02d:", m);
            firstDone = true;
        }
        tmpTime%= GET_M_FROM_S;

        int s = (int) Math.floor(tmpTime);
        ret = String.format(ret+"%02d", s);

        return ret;
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
