package com.github.alyexe.gravity.scenes;

import android.graphics.Color;
import com.github.alyexe.gravity.R;
import com.github.alyexe.gravity.utilites.GameSettings;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.SceneFW;

public class TopDistance extends SceneFW {

    String[] numbers = new String[5];

    public TopDistance(CoreFW coreFW) {
        super(coreFW);
        for (int i = 0; i < 5; i++) {
            this.numbers[i] = " " + (i + 1) + ". " + GameSettings.distance[i];
        }
    }

    @Override
    public void update() {
        if (coreFW.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            coreFW.setSceneFW(new MainMenuScene(coreFW));
        }
    }

    @Override
    public void drawing() {
        graphicsFW.drawText(coreFW.getString(R.string.txt_top_distance), 150, 200, Color.GREEN, 40, null);
        graphicsFW.drawText(String.valueOf(numbers[0]), 120, 240, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(numbers[1]), 120, 280, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(numbers[2]), 120, 320, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(numbers[3]), 120, 360, Color.GREEN, 35, null);
        graphicsFW.drawText(String.valueOf(numbers[4]), 120, 400, Color.GREEN, 35, null);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        graphicsFW.clearScene(Color.BLACK);
    }

    @Override
    public void dispose() {

    }
}
