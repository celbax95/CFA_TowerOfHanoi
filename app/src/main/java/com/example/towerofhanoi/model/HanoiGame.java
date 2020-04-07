package com.example.towerofhanoi.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HanoiGame {

    public static final int ROD_COUNT = 3;

    private List<Rod> rods;

    public HanoiGame() {
        rods = new ArrayList<>(ROD_COUNT);
    }

    public Rod getRod(int id) {
        return rods.get(id);
    }

    public void initGame(int diskCount) {
        for (int i = 0; i< ROD_COUNT; i++) {
            rods.set(i,new Rod(i, diskCount));
        }

        Rod firstRod = rods.get(0);

        for (int i = diskCount; i > 0; i--) {
            firstRod.addDisk(new Disk(i));
        }
    }
}
