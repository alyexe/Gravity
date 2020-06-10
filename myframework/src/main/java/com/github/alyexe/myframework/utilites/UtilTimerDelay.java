package com.github.alyexe.myframework.utilites;

public class UtilTimerDelay {
    private final double SECOND = 1000000000;
    private double mStartTime;

    public void startTimer() {
        mStartTime = System.nanoTime() / SECOND;
    }

    public boolean timerDelay(double seconds) {
        double mCurrentTime = System.nanoTime() / SECOND;
        double mElapsedTime = mCurrentTime - mStartTime;
        return mElapsedTime > seconds;
    }
}
