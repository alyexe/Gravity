package com.github.alyexe.gravity.objects;

import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.AnimationFW;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilRandomFW;

public class Enemy extends ObjectFW {
    AnimationFW enemyAnimation;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {

        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.enemySprite.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        switch (enemyType) {
            case 1:
                speed = UtilRandomFW.getGap(2, 5);
                enemyAnimation = new AnimationFW(3, UtilResource.enemySprite);
                break;
            case 2:
                speed = UtilRandomFW.getGap(4, 9);
                break;
        }
    }

    public void update(double playerSpeed) {
        x -= speed;
        x -= playerSpeed;
        if (x < minScreenX) {
            x = maxScreenX;
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        enemyAnimation.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW) {
        enemyAnimation.drawingAnimation(graphicsFW, x, y);
    }
}
