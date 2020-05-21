package com.github.alyexe.gravity.classes;

import com.github.alyexe.gravity.generators.BackgroundGenerator;
import com.github.alyexe.gravity.generators.EnemyGenerator;
import com.github.alyexe.gravity.objects.MainPlayer;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    MainPlayer mainPlayer;
    BackgroundGenerator backgroundGenerator;
    EnemyGenerator enemyGenerator;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = 0;
        this.mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        backgroundGenerator = new BackgroundGenerator(sceneWidth, sceneHeight);
        enemyGenerator = new EnemyGenerator(sceneWidth, sceneHeight, minScreenY);

    }

    public void update() {
        this.mainPlayer.update();
        backgroundGenerator.update(mainPlayer.getPlayerSpeed());
        enemyGenerator.update(mainPlayer.getPlayerSpeed());
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW) {
        this.mainPlayer.drawing(graphicsFW);
        backgroundGenerator.drawing(graphicsFW);
        enemyGenerator.drawing(graphicsFW);
    }
}
