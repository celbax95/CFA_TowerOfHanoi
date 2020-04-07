package com.example.towerofhanoi.model;

public interface GameListener {
    void onGameWin();

    void onMovementsChanged(int movementCount);

    void onTimeChanged(int time);

}
