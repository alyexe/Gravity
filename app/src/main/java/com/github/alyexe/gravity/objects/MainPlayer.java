package com.github.alyexe.gravity.objects;

import android.graphics.Rect;
import com.github.alyexe.gravity.classes.GameManager;
import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.AnimationFW;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilTimerDelay;

public class MainPlayer extends ObjectFW {
    private final int GRAVITY = -3;
    private final int MAX_SPEED = 15;
    private final int MIN_SPEED = 1;
    AnimationFW mainPlayerAnimation;
    AnimationFW mainPlayerBoostAnimation;
    AnimationFW mainPlayerExplosionAnimation;
    UtilTimerDelay onShieldHitTimer;
    UtilTimerDelay onGameOverTimer;
    private boolean boosting;
    private boolean enemyHit;
    private boolean isGameOver;
    private int playerShields;
    private CoreFW coreFW;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        radius = UtilResource.playerSprite.get(0).getWidth() / 4;
        speed = 3;
        playerShields = 3;
        this.boosting = false;
        this.enemyHit = false;
        this.isGameOver = false;
        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.playerSprite.get(0).getHeight();
        this.minScreenY = minScreenY;
        mainPlayerAnimation = new AnimationFW(speed, UtilResource.playerSprite);
        mainPlayerBoostAnimation = new AnimationFW(speed, UtilResource.playerBoostSprite);
        mainPlayerExplosionAnimation = new AnimationFW(speed, UtilResource.playerExplodeSprite);
        onShieldHitTimer = new UtilTimerDelay();
        onGameOverTimer = new UtilTimerDelay();
    }

    public void update() {
        if (coreFW.getTouchListenerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)) {
            startBoosting();
        }

        if (coreFW.getTouchListenerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)) {
            stopBoosting();
        }

        if (boosting) {
            speed += 0.2;
        } else speed -= 3;

        if (speed > MAX_SPEED) speed = MAX_SPEED;
        if (speed < MIN_SPEED) speed = MIN_SPEED;

        y -= speed + GRAVITY;
        if (y < minScreenY) y = minScreenY;
        if (y > maxScreenY) y = maxScreenY;
        if (boosting) {
            mainPlayerBoostAnimation.runAnimation();
        } else mainPlayerAnimation.runAnimation();

        hitBox = new Rect(x, y, UtilResource.enemySprite.get(0).getWidth(), UtilResource.playerSprite.get(0).getHeight());

        if (isGameOver) {
            mainPlayerExplosionAnimation.runAnimation();
        }
    }

    private void stopBoosting() {
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if (!isGameOver) {
            if (!enemyHit) {
                if (boosting) {
                    mainPlayerBoostAnimation.drawingAnimation(graphicsFW, x, y);
                } else mainPlayerAnimation.drawingAnimation(graphicsFW, x, y);
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy, x, y);
                if (onShieldHitTimer.timerDelay(1)) {
                    enemyHit = false;
                } else enemyHit = true;
            }
        } else {
            mainPlayerExplosionAnimation.drawingAnimation(graphicsFW, x, y);
            if (onGameOverTimer.timerDelay(1)) {
                GameManager.gameOver = true;
            }
        }
    }

    public int getPlayerShields() {
        return playerShields;
    }

    public double getPlayerSpeed() {
        return speed;
    }

    public void hitEnemy() {
        playerShields--;
        if (playerShields < 0) {
            UtilResource.explode.play(10f);
            isGameOver = true;
            onGameOverTimer.startTimer();
        }
        enemyHit = true;
        onShieldHitTimer.startTimer();
    }
}
