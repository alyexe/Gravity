package com.github.alyexe.gravity.objects;

import android.graphics.Rect;
import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.AnimationFW;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilRandomFW;

public class Enemy extends ObjectFW {
    private AnimationFW enemyAnimation;

    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        init(maxScreenX, maxScreenY, minScreenY, enemyType);
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        setMaxScreenX(maxScreenX);
        setMaxScreenY(maxScreenY - UtilResource.enemySprite.get(0).getHeight());
        setMinScreenY(minScreenY);
        setMinScreenX(0);
        setX(maxScreenX);
        setY(UtilRandomFW.getGap(minScreenY, maxScreenY));
        setRadius(UtilResource.enemySprite.get(0).getWidth() / 4f);
        switch (enemyType) {
            case 1:
                setSpeed(UtilRandomFW.getGap(2, 6));
                enemyAnimation = new AnimationFW(getSpeed(), UtilResource.enemySprite);
                break;
            case 2:
                setSpeed(UtilRandomFW.getGap(4, 9));
                break;
        }
    }

    public void update(double playerSpeed) {
        setX((int) (getX() - getSpeed()));
        setX((int) (getX() - playerSpeed));
        if (getX() < getMinScreenX()) {
            setX(getMaxScreenX());
            setY(UtilRandomFW.getGap(getMinScreenY(), getMaxScreenY()));
            setSpeed(UtilRandomFW.getGap(2, 6));
        }
        setHitBox(new Rect(getX(), getY(), UtilResource.enemySprite.get(0).getWidth(), UtilResource.enemySprite.get(0).getHeight()));

        enemyAnimation.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW) {
        enemyAnimation.drawingAnimation(graphicsFW, getX(), getY());
    }
}
