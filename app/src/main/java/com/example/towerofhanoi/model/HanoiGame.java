package com.example.towerofhanoi.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HanoiGame {

    public static final int ROD_COUNT = 3;

    private List<Rod> rods;

    public HanoiGame() {
        rods = new ArrayList<>();
    }

    public Rod getRod(int id) {
        return rods.get(id);
    }

    public List<Disk> initGame(int diskCount) {
        for (int i = 0; i < ROD_COUNT; i++) {
            rods.add(new Rod(i, diskCount));
        }

        Rod firstRod = rods.get(0);

        ArrayList<Disk> disks = new ArrayList<>();

        for (int i = diskCount; i > 0; i--) {
            Disk d = new Disk(firstRod, i);

            disks.add(d);

            firstRod.addDisk(d);
        }

        return disks;
    }

    private void d(Object o) {
        Log.d("HANOI_GAME", o.toString());
    }
}
