package com.github.alyexe.gravity.classes;

import com.github.alyexe.gravity.generators.BackgroundGenerator;
import com.github.alyexe.gravity.generators.EnemyGenerator;
import com.github.alyexe.gravity.objects.Enemy;
import com.github.alyexe.gravity.objects.Hud;
import com.github.alyexe.gravity.objects.MainPlayer;
import com.github.alyexe.myframework.CollisionDetect;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;
    private int passedDistance;

    public int getPassedDistance() {
        return passedDistance;
    }

    private int currentPlayerSpeed;
    private int currentPlayerShields;
    public static boolean gameOver;

    MainPlayer mainPlayer;
    BackgroundGenerator backgroundGenerator;
    EnemyGenerator enemyGenerator;
    Hud hud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        hud = new Hud(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = hud.getHUD_HEIGHT();
        this.mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        backgroundGenerator = new BackgroundGenerator(sceneWidth, sceneHeight, minScreenY);
        enemyGenerator = new EnemyGenerator(sceneWidth, sceneHeight, minScreenY);
        gameOver = false;

    }

    public void update() {
        this.mainPlayer.update();
        backgroundGenerator.update(mainPlayer.getPlayerSpeed());
        enemyGenerator.update(mainPlayer.getPlayerSpeed());

        passedDistance += mainPlayer.getPlayerSpeed();
        currentPlayerSpeed = (int)mainPlayer.getPlayerSpeed() * 60;
        currentPlayerShields = mainPlayer.getPlayerShields();
        hud.update(passedDistance, currentPlayerSpeed, currentPlayerShields);

        checkHit();
    }

    private void checkHit() {
        for (int i = 0; i < enemyGenerator.enemyList.size(); i++) {
            if (CollisionDetect.isCollisionDetected(mainPlayer, enemyGenerator.enemyList.get(i))) {
                mainPlayer.hitEnemy();
                enemyGenerator.hitPlayer(enemyGenerator.enemyList.get(i));
            }
        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        this.mainPlayer.drawing(graphicsFW);
        backgroundGenerator.drawing(graphicsFW);
        enemyGenerator.drawing(graphicsFW);
        hud.drawing(graphicsFW);
    }
}
