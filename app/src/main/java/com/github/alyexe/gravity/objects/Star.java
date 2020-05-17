package com.github.alyexe.gravity.objects;

import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilRandomFW;

public class Star extends ObjectFW {

    public Star(int sceneWidth, int sceneHeight) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = 0;
        this.speed = 4;
        this.x = UtilRandomFW.getCasualNumber(maxScreenX);
        this.y = UtilRandomFW.getCasualNumber(maxScreenY);
    }

    public void update() {
        x -= speed;
        if (x < 0) {
            x = maxScreenX;
            y = UtilRandomFW.getCasualNumber(maxScreenY);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
