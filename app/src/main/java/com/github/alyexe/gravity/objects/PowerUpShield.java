package com.github.alyexe.gravity.objects;

import android.graphics.Rect;
import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.AnimationFW;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilRandomFW;

public class PowerUpShield extends ObjectFW {
    private AnimationFW mPowerUpShieldAnimation;

    public PowerUpShield(int maxScreenX, int maxScreenY, int minScreenY) {
        init(maxScreenX, maxScreenY, minScreenY);
    }

    private void init(int maxScreenX, int maxScreenY, int minScreenY) {
        setMaxScreenX(maxScreenX);
        setMaxScreenY(maxScreenY - UtilResource.PowerUpShieldSprite.get(0).getHeight());
        setMinScreenY(minScreenY);
        setMinScreenX(0);
        setX(maxScreenX);
        setY(UtilRandomFW.getGap(minScreenY, maxScreenY));
        setRadius(UtilResource.PowerUpShieldSprite.get(0).getWidth() / 2f);
        setSpeed(UtilRandomFW.getGap(2, 6));
        setHitBox(new Rect(getX(), getY(), UtilResource.PowerUpShieldSprite.get(0).getWidth(), UtilResource.PowerUpShieldSprite.get(0).getHeight()));
        mPowerUpShieldAnimation = new AnimationFW(getSpeed(), UtilResource.PowerUpShieldSprite);
    }

    public void update(double playerSpeed) {
        setX((int) (getX() - getSpeed()));
        setX((int) (getX() - playerSpeed));
        if (getX() < getMinScreenX()) {
            setY(UtilRandomFW.getGap(getMinScreenY(), getMaxScreenY()));
        }
        setHitBox(new Rect(getX(), getY(), UtilResource.PowerUpShieldSprite.get(0).getWidth(), UtilResource.PowerUpShieldSprite.get(0).getHeight()));

        mPowerUpShieldAnimation.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW) {
        mPowerUpShieldAnimation.drawingAnimation(graphicsFW, getX(), getY());
    }
}
