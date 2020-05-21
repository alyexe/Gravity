package com.github.alyexe.gravity.objects;

import com.github.alyexe.myframework.AnimationFW;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.gravity.utilites.UtilResource;

public class MainPlayer extends ObjectFW {
    private final int GRAVITY = -3;
    private final int MAX_SPEED = 15;
    private final int MIN_SPEED = 1;
    private boolean boosting;
    private int playerShields;
    private CoreFW coreFW;

    AnimationFW mainPlayerAnimation;
    AnimationFW mainPlayerBoostAnimation;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 3;
        playerShields = 3;
        this.boosting = false;
        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.playerSprite.get(0).getHeight();
        this.minScreenY = minScreenY;
        mainPlayerAnimation = new AnimationFW(speed, UtilResource.playerSprite);
        mainPlayerBoostAnimation = new AnimationFW(speed, UtilResource.playerBoostSprite);
    }

    public void update() {
        if (coreFW.getTouchListenerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }

        if (coreFW.getTouchListenerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }

        if (boosting) {
            speed += 0.1;
        } else speed -= 3;

        if (speed > MAX_SPEED) speed = MAX_SPEED;
        if (speed < MIN_SPEED) speed = MIN_SPEED;

        y -= speed + GRAVITY;
        if (y < minScreenY) y = minScreenY;
        if (y > maxScreenY) y = maxScreenY;
        if (boosting) {
            mainPlayerBoostAnimation.runAnimation();
        } else mainPlayerAnimation.runAnimation();
    }

    private void stopBoosting() {
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if (boosting) {
            mainPlayerBoostAnimation.drawingAnimation(graphicsFW, x, y);
        } else mainPlayerAnimation.drawingAnimation(graphicsFW, x, y);
    }

    public int getPlayerShields() {
        return playerShields;
    }

    public double getPlayerSpeed() {
        return speed;
    }
}
