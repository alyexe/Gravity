package com.github.alyexe.gravity.generators;

import com.github.alyexe.gravity.objects.PowerUpShield;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.utilites.UtilTimerDelay;

public class PowerUpGenerator {
    private int mMaxScreenX;
    private int mMaxScreenY;
    private int mMinScreenY;

    private UtilTimerDelay mPowerUpTimer;
    private PowerUpShield mPowerUpShield;

    public PowerUpGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        mMaxScreenX = sceneWidth;
        mMaxScreenY = sceneHeight;
        mMinScreenY = minScreenY;
        mPowerUpShield = new PowerUpShield(mMaxScreenX, mMaxScreenY, minScreenY);
        mPowerUpTimer = new UtilTimerDelay();
        mPowerUpTimer.startTimer();
    }

    public void drawing(GraphicsFW graphicsFW) {
        mPowerUpShield.drawing(graphicsFW);
    }

    public void update(double playerSpeed) {
        if (mPowerUpTimer.timerDelay(5)) {
            mPowerUpShield.update(playerSpeed);
            if (mPowerUpShield.getX() < 0) {
                mPowerUpShield = null;
                mPowerUpShield = new PowerUpShield(mMaxScreenX, mMaxScreenY, mMinScreenY);
                mPowerUpTimer.startTimer();
            }
        }
    }

    public void hitPlayer() {
        mPowerUpShield = null;
        mPowerUpShield = new PowerUpShield(mMaxScreenX, mMaxScreenY, mMinScreenY);
        mPowerUpTimer.startTimer();
    }

    public PowerUpShield getPowerUpShield() {
        return this.mPowerUpShield;
    }
}
