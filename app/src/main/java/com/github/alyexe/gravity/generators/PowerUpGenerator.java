package com.github.alyexe.gravity.generators;

import com.github.alyexe.gravity.objects.PowerUpShield;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.utilites.UtilRandomFW;
import com.github.alyexe.myframework.utilites.UtilTimerDelay;

public class PowerUpGenerator {
    private final int maxScreenX;
    private final int maxScreenY;
    private final int minScreenX;
    private final int minScreenY;

    UtilTimerDelay powerUpTimer;
    PowerUpShield powerUpShield;

    public PowerUpGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        powerUpShield = new PowerUpShield(maxScreenX, maxScreenY, minScreenY);
        powerUpTimer = new UtilTimerDelay();
        powerUpTimer.startTimer();
    }

    public void drawing(GraphicsFW graphicsFW) {
        powerUpShield.drawing(graphicsFW);
    }

    public void update(double playerSpeed) {
        if (powerUpTimer.timerDelay(UtilRandomFW.getGap(5, 20))) {
            powerUpShield.update(playerSpeed);
            if (powerUpShield.getX() < 0) {
                powerUpShield = null;
                powerUpShield = new PowerUpShield(maxScreenX, maxScreenY, minScreenY);
                powerUpTimer.startTimer();
            }
        }
    }

    public PowerUpShield getPowerUpShield() {
        return this.powerUpShield;
    }

    public void hitPlayer() {
        powerUpShield = null;
        powerUpShield = new PowerUpShield(maxScreenX, maxScreenY, minScreenY);
        powerUpTimer.startTimer();
    }
}
