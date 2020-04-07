package com.example.towerofhanoi.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Rod {
    private Stack<Disk> disks;

    public int maxSize;

    public Rod(int id, int maxSize) {
        disks = new Stack<Disk>();
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
        if (isEmpty()) {
            disks.add(disk);
            disk.setHolder(this);
            return true;
        } else if (disks.peek().getSize() > disk.getSize() && !isFull()) {
            disks.add(disk);
            disk.setHolder(this);
            return true;
        } else {
            return false;
        }
    }
}
