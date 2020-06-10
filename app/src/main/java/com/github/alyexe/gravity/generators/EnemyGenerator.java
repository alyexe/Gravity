package com.github.alyexe.gravity.generators;

import com.github.alyexe.gravity.objects.Enemy;
import com.github.alyexe.myframework.GraphicsFW;

import java.util.ArrayList;
import java.util.List;

public class EnemyGenerator {
    public List<Enemy> enemyList;
    private int mMaxScreenX;
    private int mMaxScreenY;
    private int mMinScreenY;

    public EnemyGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        mMaxScreenX = sceneWidth;
        mMaxScreenY = sceneHeight;
        mMinScreenY = minScreenY;
        enemyList = new ArrayList<>();
    }

    public void update(double playerSpeed) {
        if (enemyList.size() < 3) {
            addEnemy(1);
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

    private void addEnemy(int enemiesAmount) {
        while (enemiesAmount > 0) {
            enemyList.add(new Enemy(mMaxScreenX, mMaxScreenY, mMinScreenY, 1));
            enemiesAmount--;
        }
    }

    public void hitPlayer(Enemy e) {
        enemyList.remove(e);
    }
}
