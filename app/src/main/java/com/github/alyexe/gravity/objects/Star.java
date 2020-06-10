package com.github.alyexe.gravity.objects;

import com.github.alyexe.myframework.ObjectFW;
import com.github.alyexe.myframework.utilites.UtilRandomFW;

public class Star extends ObjectFW {

    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        init(sceneWidth, sceneHeight, minScreenY);
    }

    private void init(int sceneWidth, int sceneHeight, int minScreenY) {
        setMaxScreenX(sceneWidth);
        setMaxScreenY(sceneHeight);
        setMinScreenX(0);
        setMinScreenY(minScreenY);
        setSpeed(4);
        setX(UtilRandomFW.getCasualNumber(getMaxScreenX()));
        setY(UtilRandomFW.getGap(getMinScreenY(), getMaxScreenY()));
    }

    public void update(double playerSpeed) {
        setX((int) (getX() - playerSpeed));
        setX((int) (getX() - getSpeed()));
        if (getX() < 0) {
            setX(getMaxScreenX());
            setY(UtilRandomFW.getGap(getMinScreenY(), getMaxScreenY()));
        }
    }
}
