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
    private final int MAX_SPEED = 20;
    private final int MIN_SPEED = 1;
    private final CoreFW coreFW;
    private boolean isShieldsOn;
    AnimationFW mainPlayerAnimation;
    AnimationFW mainPlayerBoostAnimation;
    AnimationFW mainPlayerExplosionAnimation;
    AnimationFW mainPlayerShieldsOnAnimation;
    AnimationFW mainPlayerBoostShieldsOnAnimation;
    UtilTimerDelay onShieldHitTimer;
    UtilTimerDelay onGameOverTimer;
    UtilTimerDelay onShieldsOnTimer;
    private boolean boosting;
    private boolean enemyHit;
    private boolean isGameOver;
    private int playerShields;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = GameManager.ANIMATION_SPEED;
        radius = UtilResource.playerSprite.get(0).getWidth() / 4f;
        playerShields = 1;
        this.isShieldsOn = false;
        this.boosting = false;
        this.enemyHit = false;
        this.isGameOver = false;
        this.coreFW = coreFW;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.playerSprite.get(0).getHeight();
        this.minScreenY = minScreenY;
        onShieldHitTimer = new UtilTimerDelay();
        onGameOverTimer = new UtilTimerDelay();
        onShieldsOnTimer = new UtilTimerDelay();

        initAnimation();
    }

    private void initAnimation() {
        mainPlayerAnimation = new AnimationFW(speed, UtilResource.playerSprite);
        mainPlayerBoostAnimation = new AnimationFW(speed, UtilResource.playerBoostSprite);
        mainPlayerExplosionAnimation = new AnimationFW(speed, UtilResource.playerExplodeSprite);
        mainPlayerShieldsOnAnimation = new AnimationFW(speed, UtilResource.playerShieldsOnSprite);
        mainPlayerBoostShieldsOnAnimation = new AnimationFW(speed, UtilResource.playerBoostShieldsOnSprite);
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
            if (isShieldsOn) {
                mainPlayerBoostShieldsOnAnimation.runAnimation();
            } else mainPlayerBoostAnimation.runAnimation();
        } else if (isShieldsOn) {
            mainPlayerShieldsOnAnimation.runAnimation();
        } else mainPlayerAnimation.runAnimation();

        hitBox = new Rect(x, y, UtilResource.enemySprite.get(0).getWidth(), UtilResource.playerSprite.get(0).getHeight());

        if (isShieldsOn && onShieldsOnTimer.timerDelay(5)) {
            isShieldsOn = false;
        }

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
                    if (isShieldsOn) {
                        mainPlayerBoostShieldsOnAnimation.drawingAnimation(graphicsFW, x, y);
                    } else mainPlayerBoostAnimation.drawingAnimation(graphicsFW, x, y);
                } else if (isShieldsOn) {
                    mainPlayerShieldsOnAnimation.drawingAnimation(graphicsFW, x, y);
                } else mainPlayerAnimation.drawingAnimation(graphicsFW, x, y);
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy, x, y);
                enemyHit = !onShieldHitTimer.timerDelay(1);
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
        if (!isShieldsOn) {
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

    public void hitPowerUp() {
        isShieldsOn = true;
        onShieldsOnTimer.startTimer();
    }
}
