package com.github.alyexe.gravity.generators;

import com.github.alyexe.gravity.objects.Enemy;
import com.github.alyexe.myframework.GraphicsFW;

import java.util.ArrayList;
import java.util.List;

public class EnemyGenerator {
    private final int maxScreenX;
    private final int maxScreenY;
    private final int minScreenX;
    private final int minScreenY;

    public List<Enemy> enemyList;

    public EnemyGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        enemyList = new ArrayList<>();
    }

    public void update(double playerSpeed) {
        if (enemyList.size() < 3) {
            addEnemy(playerSpeed, 3);
        }
        for (Enemy e : enemyList) {
            e.update(playerSpeed);
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (Enemy e : enemyList) {
            e.drawing(graphicsFW);
        }
    }

    private void addEnemy(double playerSpeed, int enemiesAmount) {
        while (enemiesAmount > 0) {
            enemyList.add(new Enemy(maxScreenX, maxScreenY, minScreenY, 1));
            enemiesAmount--;
        }
    }

    public void hitPlayer(Enemy e) {
        enemyList.remove(e);
    }
}
