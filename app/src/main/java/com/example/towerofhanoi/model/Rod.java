package com.example.towerofhanoi.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Rod {
    private Stack<Disk> disks;

    int id;

    public int maxSize;

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getId() {
        return id;
    }

    public Rod(int id, int maxSize) {
        this.id = id;
        disks = new Stack<Disk>();
        this.maxSize = maxSize;
    }

    public void setDisks(List<Disk> disks) {
        this.disks.addAll(disks);
    }

    public Disk getTopDisk() {
        return disks.peek();
    }

    public Disk getAndRemove() {
        return disks.pop();
    }

    public boolean isEmpty() {
        return disks.isEmpty();
    }

    public int getDiskCount() {
        return disks.size();
    }

    public void removeAllDisks() {
        disks.removeAllElements();
    }

    public boolean isFull() {
        return disks.size() == maxSize;
    }

    public boolean addDisk(Disk disk) {
        if (isEmpty() || (disks.peek().getSize() > disk.getSize() && !isFull())) {
            disks.add(disk);
            disk.setHolder(this);
            disk.setHeight(getDiskCount());
            return true;
        } else {
            return false;
        }
    }
}
