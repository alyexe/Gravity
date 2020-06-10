package com.github.alyexe.gravity.classes;

import com.github.alyexe.gravity.generators.BackgroundGenerator;
import com.github.alyexe.gravity.generators.EnemyGenerator;
import com.github.alyexe.gravity.generators.PowerUpGenerator;
import com.github.alyexe.gravity.objects.Enemy;
import com.github.alyexe.gravity.objects.Hud;
import com.github.alyexe.gravity.objects.MainPlayer;
import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.CollisionDetect;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;
    private int passedDistance;
    private int currentPlayerSpeed;
    private int currentPlayerShields;
    public static boolean gameOver;

    public final static double ANIMATION_SPEED = 5;

    MainPlayer mainPlayer;
    BackgroundGenerator backgroundGenerator;
    EnemyGenerator enemyGenerator;
    PowerUpGenerator powerUpGenerator;
    Hud hud;

    public int getPassedDistance() {
        return passedDistance;
    }

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new Hud(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = hud.getHUD_HEIGHT();
        this.mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        backgroundGenerator = new BackgroundGenerator(sceneWidth, sceneHeight, minScreenY);
        enemyGenerator = new EnemyGenerator(sceneWidth, sceneHeight, minScreenY);
        powerUpGenerator = new PowerUpGenerator(sceneWidth, sceneHeight, minScreenY);
        gameOver = false;

    }

    public void update() {
        this.mainPlayer.update();
        backgroundGenerator.update(mainPlayer.getPlayerSpeed());
        enemyGenerator.update(mainPlayer.getPlayerSpeed());
        powerUpGenerator.update(mainPlayer.getPlayerSpeed());

        passedDistance += mainPlayer.getPlayerSpeed();
        currentPlayerSpeed = (int)mainPlayer.getPlayerSpeed() * 60;
        currentPlayerShields = mainPlayer.getPlayerShields();
        hud.update(passedDistance, currentPlayerSpeed, currentPlayerShields);

        checkHit();
    }

    private void checkHit() {
        for (int i = 0; i < enemyGenerator.enemyList.size(); i++) {
            if (CollisionDetect.isCollisionDetected(mainPlayer, enemyGenerator.enemyList.get(i))) {
                UtilResource.hit.play(10);
                mainPlayer.hitEnemy();
                enemyGenerator.hitPlayer(enemyGenerator.enemyList.get(i));
            }
        }
        if (CollisionDetect.isCollisionDetected(mainPlayer, powerUpGenerator.getPowerUpShield())) {
            mainPlayer.hitPowerUp();
            powerUpGenerator.hitPlayer();
        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        this.mainPlayer.drawing(graphicsFW);
        backgroundGenerator.drawing(graphicsFW);
        enemyGenerator.drawing(graphicsFW);
        powerUpGenerator.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }
}
