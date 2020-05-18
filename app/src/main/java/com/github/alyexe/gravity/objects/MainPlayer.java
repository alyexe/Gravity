package com.github.alyexe.gravity.objects;

import com.github.alyexe.gravity.classes.GameAnimation;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilResourceFW;

public class MainPlayer extends ObjectFW {
    private final int GRAVITY = -3;
    private final int MAX_SPEED = 15;
    private final int MIN_SPEED = 1;

    GameAnimation mainPlayerSpriteAnimation;

    public MainPlayer(int maxScreenX, int maxScreenY, int minScreenY) {
        x = 20;
        y = 200;
        speed = 1;
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResourceFW.playerSprite.get(0).getHeight();
        mainPlayerSpriteAnimation = new GameAnimation(speed, UtilResourceFW.playerSprite);
    }

    public void update() {
        y -= speed + GRAVITY;
        if (y < minScreenY) y = minScreenY;
        if (y > maxScreenY) y = maxScreenY;
        mainPlayerSpriteAnimation.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW) {
        mainPlayerSpriteAnimation.drawingAnimation(graphicsFW, x, y);
    }
}
