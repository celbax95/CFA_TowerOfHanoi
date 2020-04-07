package com.example.towerofhanoi.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Rod {
    private Stack<Disk> disks;

    private int id;

    private int maxSize;

    private RodListener rodListener;

    public RodListener getRodListener() {
        return rodListener;
    }

    public void setRodListener(RodListener rodListener) {
        this.rodListener = rodListener;
    }

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
        try {
            return disks.get(disks.size()-1);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean canAdd(Disk d) {
        if (getDiskCount() != 0) {
            return getTopDisk().getSize() > d.getSize();
        }
        return true;
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
        if (isEmpty() || (getTopDisk().getSize() > disk.getSize() && !isFull())) {
            disks.add(disk);
            disk.setHolder(this);
            disk.setHeight(getDiskCount());

            if (getDiskCount() == maxSize) {
                if (rodListener != null) {
                    rodListener.onRodFull(this);
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
