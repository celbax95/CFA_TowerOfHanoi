package com.example.towerofhanoi.model;

public class Score {
    private static final int UNDEFINED = -1;

    private long date;
    private int disks;
    private int moves;
    private int time;

    public Score() {
        date = System.currentTimeMillis();
        disks = UNDEFINED;
        moves = UNDEFINED;
        time = UNDEFINED;
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

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
