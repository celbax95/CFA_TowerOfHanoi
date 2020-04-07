package com.example.towerofhanoi.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class HanoiGame implements RodListener {

    private static final int LEFT_ROD = 0;
    private static final int MIDDLE_ROD = 1;
    private static final int RIGHT_ROD = 2;

    private static final int ROD_COUNT = 3;

    private int moveCount;

    private List<Rod> rods;

    private GameListener gameListener;

    public GameListener getGameListener() {
        return gameListener;
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public HanoiGame() {
        rods = new ArrayList<>();
    }

    public void addMove() {
        gameListener.onMovementsChanged(++moveCount);
    }

    public Rod getRod(int id) {
        return rods.get(id);
    }

    public List<Disk> initGame(int diskCount) {

        rods.removeAll(rods);

        for (int i = 0; i < ROD_COUNT; i++) {
            Rod r = new Rod(i, diskCount);
            rods.add(r);
        }

        rods.get(RIGHT_ROD).setRodListener(this);

        Rod firstRod = rods.get(LEFT_ROD);

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

    @Override
    public void onRodFull(Rod rod) {
        if (rod.getId() == RIGHT_ROD) {
            if (gameListener != null) {
                gameListener.onGameWin();
            }
        }
    }
}
