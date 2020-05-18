package com.github.alyexe.gravity.classes;

import com.github.alyexe.gravity.objects.MainPlayer;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    MainPlayer mainPlayer;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = 0;
        this.mainPlayer = new MainPlayer(maxScreenX, maxScreenY, minScreenY);
    }

    public void update() {
        this.mainPlayer.update();
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        this.mainPlayer.drawing(graphicsFW);
    }
}
