package com.github.alyexe.gravity.objects;

import android.graphics.Rect;
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
        radius = UtilResource.enemySprite.get(0).getWidth()/4;
        switch (enemyType) {
            case 1:
                speed = UtilRandomFW.getGap(2, 6);
                enemyAnimation = new AnimationFW(speed, UtilResource.enemySprite);
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
        hitBox = new Rect(x, y, UtilResource.enemySprite.get(0).getWidth(), UtilResource.enemySprite.get(0).getHeight());

        enemyAnimation.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW) {
        enemyAnimation.drawingAnimation(graphicsFW, x, y);
    }
}
