package com.github.alyexe.gravity.objects;

import android.graphics.Rect;
import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.AnimationFW;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilRandomFW;

public class PowerUpShield extends ObjectFW {
    AnimationFW powerUpShieldAnimation;

    public PowerUpShield(int maxScreenX, int maxScreenY, int minScreenY) {

        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.PowerUpShieldSprite.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX;
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = UtilResource.PowerUpShieldSprite.get(0).getWidth() / 2f;
        speed = UtilRandomFW.getGap(2, 6);
        powerUpShieldAnimation = new AnimationFW(speed, UtilResource.PowerUpShieldSprite);
        hitBox = new Rect(x, y, UtilResource.PowerUpShieldSprite.get(0).getWidth(), UtilResource.PowerUpShieldSprite.get(0).getHeight());
    }

    public void update(double playerSpeed) {
        x -= speed;
        x -= playerSpeed;
        if (x < minScreenX) {
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        hitBox = new Rect(x, y, UtilResource.PowerUpShieldSprite.get(0).getWidth(), UtilResource.PowerUpShieldSprite.get(0).getHeight());

        powerUpShieldAnimation.runAnimation();
    }

    public void drawing(GraphicsFW graphicsFW) {
        powerUpShieldAnimation.drawingAnimation(graphicsFW, x, y);
    }
}
