package com.example.towerofhanoi.model;

import android.util.Log;
import android.widget.Chronometer;

import java.util.ArrayList;
import java.util.List;

public class HanoiGame implements RodListener {

    public int getMoveCount() {
        return moveCount;
    }

    public long getSecondsSinceStarted() {
        return secondsSinceStarted;
    }

    private static final int A_SECOND = 1000;

    private static final int LEFT_ROD = 0;
    private static final int MIDDLE_ROD = 1;
    private static final int RIGHT_ROD = 2;

    private static final int ROD_COUNT = 3;

    private int moveCount;

    private long secondsSinceStarted;

    private List<Rod> rods;

    private Thread timerThread;

    private GameListener gameListener;

    public HanoiGame() {
        rods = new ArrayList<>();
        moveCount = 0;
        secondsSinceStarted = 0;
    }

    public GameListener getGameListener() {
        return gameListener;
    }

    public void setGameListener(GameListener gameListener) {
        this.gameListener = gameListener;
    }

    public void addMove() {
        if (moveCount == 0) {
            secondsSinceStarted = 0;
            startTimer();
        }
        gameListener.onMovementsChanged(++moveCount);
    }

    public void stopTimer() {
        if (timerThread != null) {
            timerThread.interrupt();
        }
        timerThread = null;
    }

    private void startTimer() {
        stopTimer();
        timerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    d("RUN");
                    try {
                        Thread.sleep(A_SECOND);
                    } catch (InterruptedException e) {
                        return;
                    }

                    gameListener.onTimeChanged(++secondsSinceStarted);
                }
            }
        });
        timerThread.setName("HanoiGame_TimeCounter");
        timerThread.start();
    }

    public void setPause(boolean pause) {
        if (pause) {
            stopTimer();
        } else {
            if (moveCount > 0) {
                startTimer();
            }
        }

    }

    public Rod getRod(int id) {
        return rods.get(id);
    }

    public List<Disk> initGame(int diskCount) {
        stopTimer();
        secondsSinceStarted = 0;
        gameListener.onTimeChanged(0);
        moveCount = 0;
        gameListener.onMovementsChanged(moveCount);


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
            stopTimer();
            if (gameListener != null) {
                gameListener.onGameWin();
            }
        }
    }
}
