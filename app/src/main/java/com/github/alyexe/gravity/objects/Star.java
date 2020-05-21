package com.github.alyexe.gravity.objects;

import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilRandomFW;

public class Star extends ObjectFW {

    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        this.speed = 4;
        this.x = UtilRandomFW.getCasualNumber(maxScreenX);
        this.y = UtilRandomFW.getGap(this.minScreenY, maxScreenY);
    }

    public void update(double playerSpeed) {
        x -= playerSpeed;
        x -= speed;
        if (x < 0) {
            x = maxScreenX;
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
