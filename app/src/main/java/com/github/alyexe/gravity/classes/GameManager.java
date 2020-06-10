package com.github.alyexe.gravity.classes;

import com.github.alyexe.gravity.generators.BackgroundGenerator;
import com.github.alyexe.gravity.generators.EnemyGenerator;
import com.github.alyexe.gravity.generators.PowerUpGenerator;
import com.github.alyexe.gravity.objects.Hud;
import com.github.alyexe.gravity.objects.MainPlayer;
import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.CollisionDetect;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

public class GameManager {
    public final static double ANIMATION_SPEED = 5;
    public static boolean gameOver;
    private int mPassedDistance;
    private MainPlayer mMainPlayer;
    private BackgroundGenerator mBackgroundGenerator;
    private EnemyGenerator mEnemyGenerator;
    private PowerUpGenerator mPowerUpGenerator;
    private Hud mHud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        init(coreFW, sceneWidth, sceneHeight);
    }

    private void init(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        mHud = new Hud(coreFW);
        int minScreenY = mHud.getHudHeight();
        mMainPlayer = new MainPlayer(coreFW, sceneWidth, sceneHeight, minScreenY);
        mBackgroundGenerator = new BackgroundGenerator(sceneWidth, sceneHeight, minScreenY);
        mEnemyGenerator = new EnemyGenerator(sceneWidth, sceneHeight, minScreenY);
        mPowerUpGenerator = new PowerUpGenerator(sceneWidth, sceneHeight, minScreenY);
        gameOver = false;
    }

    public void update() {
        mMainPlayer.update();
        mBackgroundGenerator.update(mMainPlayer.getSpeed());
        mEnemyGenerator.update(mMainPlayer.getSpeed());
        mPowerUpGenerator.update(mMainPlayer.getSpeed());

        mPassedDistance += mMainPlayer.getSpeed();
        int currentPlayerSpeed = (int) mMainPlayer.getSpeed() * 60;
        int currentPlayerShields = mMainPlayer.getPlayerShields();
        mHud.update(mPassedDistance, currentPlayerSpeed, currentPlayerShields);

        checkHit();
    }

    private void checkHit() {
        for (int i = 0; i < mEnemyGenerator.enemyList.size(); i++) {
            if (CollisionDetect.isCollisionDetected(mMainPlayer, mEnemyGenerator.enemyList.get(i))) {
                UtilResource.hit.play(10);
                mMainPlayer.hitEnemy();
                mEnemyGenerator.hitPlayer(mEnemyGenerator.enemyList.get(i));
            }
        }
        if (CollisionDetect.isCollisionDetected(mMainPlayer, mPowerUpGenerator.getPowerUpShield())) {
            mMainPlayer.hitPowerUp();
            mPowerUpGenerator.hitPlayer();
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        this.mMainPlayer.drawing(graphicsFW);
        mBackgroundGenerator.drawing(graphicsFW);
        mEnemyGenerator.drawing(graphicsFW);
        mPowerUpGenerator.drawing(graphicsFW);
        mHud.drawing(graphicsFW);
    }

    public int getPassedDistance() {
        return mPassedDistance;
    }
}
