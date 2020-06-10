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
    private final CoreFW mCoreFW;
    private UtilTimerDelay mOnShieldHitTimer;
    private UtilTimerDelay mOnGameOverTimer;
    private UtilTimerDelay mOnShieldsOnTimer;
    private boolean mShieldsOn;
    private AnimationFW mMainPlayerAnimation;
    private AnimationFW mMainPlayerBoostAnimation;
    private AnimationFW mMainPlayerExplosionAnimation;
    private AnimationFW mMainPlayerShieldsOnAnimation;
    private AnimationFW mMainPlayerBoostShieldsOnAnimation;
    private boolean mBoosting;
    private boolean mEnemyHit;
    private boolean mIsGameOver;
    private int mPlayerShields;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
        mCoreFW = coreFW;
        init(maxScreenX, maxScreenY, minScreenY);
        initAnimation();
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        setX(20);
        setY(200);
        setSpeed(GameManager.ANIMATION_SPEED);
        setRadius(UtilResource.playerSprite.get(0).getWidth() / 4f);
        mPlayerShields = 1;
        mShieldsOn = false;
        mBoosting = false;
        mEnemyHit = false;
        mIsGameOver = false;
        setMaxScreenX(maxScreenX);
        setMaxScreenY(maxScreenY - UtilResource.playerSprite.get(0).getHeight());
        setMinScreenY(minScreenY);
        mOnShieldHitTimer = new UtilTimerDelay();
        mOnGameOverTimer = new UtilTimerDelay();
        mOnShieldsOnTimer = new UtilTimerDelay();
    }

    private void initAnimation() {
        mMainPlayerAnimation = new AnimationFW(getSpeed(), UtilResource.playerSprite);
        mMainPlayerBoostAnimation = new AnimationFW(getSpeed(), UtilResource.playerBoostSprite);
        mMainPlayerExplosionAnimation = new AnimationFW(getSpeed(), UtilResource.playerExplodeSprite);
        mMainPlayerShieldsOnAnimation = new AnimationFW(getSpeed(), UtilResource.playerShieldsOnSprite);
        mMainPlayerBoostShieldsOnAnimation = new AnimationFW(getSpeed(), UtilResource.playerBoostShieldsOnSprite);
    }

    public void update() {
        if (mCoreFW.getTouchListenerFW().getTouchDown(0, getMaxScreenY(), getMaxScreenX(), getMaxScreenY())) {
            startBoosting();
        }

        if (mCoreFW.getTouchListenerFW().getTouchUp(0, getMaxScreenY(), getMaxScreenX(), getMaxScreenY())) {
            stopBoosting();
        }

        if (mBoosting) {
            setSpeed(getSpeed() + 0.2);
        } else setSpeed(getSpeed() - 3);

        if (getSpeed() > MAX_SPEED) setSpeed(MAX_SPEED);
        if (getSpeed() < MIN_SPEED) setSpeed(MIN_SPEED);

        setY((int) (getY() - (getSpeed() + GRAVITY)));
        if (getY() < getMinScreenY()) setY(getMinScreenY());
        if (getY() > getMaxScreenY()) setY(getMaxScreenY());
        if (mBoosting) {
            if (mShieldsOn) {
                mMainPlayerBoostShieldsOnAnimation.runAnimation();
            } else mMainPlayerBoostAnimation.runAnimation();
        } else if (mShieldsOn) {
            mMainPlayerShieldsOnAnimation.runAnimation();
        } else mMainPlayerAnimation.runAnimation();

        setHitBox(new Rect(getX(), getY(), UtilResource.enemySprite.get(0).getWidth(), UtilResource.playerSprite.get(0).getHeight()));

        if (mShieldsOn && mOnShieldsOnTimer.timerDelay(5)) {
            mShieldsOn = false;
        }

        if (mIsGameOver) {
            mMainPlayerExplosionAnimation.runAnimation();
        }
    }

    private void stopBoosting() {
        mBoosting = false;
    }

    private void startBoosting() {
        mBoosting = true;
    }

    public void drawing(GraphicsFW graphicsFW) {
        if (!mIsGameOver) {
            if (!mEnemyHit) {
                if (mBoosting) {
                    if (mShieldsOn) {
                        mMainPlayerBoostShieldsOnAnimation.drawingAnimation(graphicsFW, getX(), getY());
                    } else mMainPlayerBoostAnimation.drawingAnimation(graphicsFW, getX(), getY());
                } else if (mShieldsOn) {
                    mMainPlayerShieldsOnAnimation.drawingAnimation(graphicsFW, getX(), getY());
                } else mMainPlayerAnimation.drawingAnimation(graphicsFW, getX(), getY());
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy, getX(), getY());
                mEnemyHit = !mOnShieldHitTimer.timerDelay(1);
            }
        } else {
            mMainPlayerExplosionAnimation.drawingAnimation(graphicsFW, getX(), getY());
            if (mOnGameOverTimer.timerDelay(1)) {
                GameManager.gameOver = true;
            }
        }
    }

    public int getPlayerShields() {
        return mPlayerShields;
    }

    public void hitEnemy() {
        if (!mShieldsOn) {
            mPlayerShields--;
            if (mPlayerShields < 0) {
                UtilResource.explode.play(10f);
                mIsGameOver = true;
                mOnGameOverTimer.startTimer();
            }
            mEnemyHit = true;
            mOnShieldHitTimer.startTimer();
        }
    }

    public void hitPowerUp() {
        mShieldsOn = true;
        mOnShieldsOnTimer.startTimer();
    }
}
