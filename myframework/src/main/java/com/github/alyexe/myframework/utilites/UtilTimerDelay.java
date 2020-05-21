package com.github.alyexe.myframework.utilites;

public class UtilTimerDelay {
    final double SECOND = 1000000000;
    double startTime;
    double currentTime;
    double elapsedTime;

    public void startTimer() {
        startTime = System.nanoTime() / SECOND;
    }

    public boolean timerDelay(double seconds) {
        currentTime = System.nanoTime() / SECOND;
        elapsedTime = currentTime - startTime;
        if (elapsedTime > seconds) return true;
        return false;
    }
}
