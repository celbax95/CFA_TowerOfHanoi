package com.example.towerofhanoi.model;

public class Disk {
    private int size;

    private Rod holder;

    public Disk(Rod holder, int size) {
        this.size = size;
        this.holder = holder;
    }

    public boolean hasHolder() {
        return holder != null;
    }

    public Rod getHolder() {
        return holder;
    }

    public void setHolder(Rod holder) {
        this.holder = holder;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
