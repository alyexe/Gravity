package com.github.alyexe.gravity.generators;

import android.graphics.Color;
import com.github.alyexe.gravity.objects.Star;
import com.github.alyexe.myframework.GraphicsFW;

import java.util.ArrayList;
import java.util.List;

public class BackgroundGenerator {

    List<Star> starList = new ArrayList<>();

    public BackgroundGenerator(int sceneWidth, int sceneHeight, int minScreenY) {
        int starsPeak = 50;
        for (int i = 0; i < starsPeak; i++) {
            Star star = new Star(sceneWidth, sceneHeight, minScreenY);
            starList.add(star);
        }
    }

    public void update(double playerSpeed) {
        for (Star s : starList) {
            s.update(playerSpeed);
        }
    }

    public void drawing(GraphicsFW graphicsFW) {
        for (Star s : starList) {
            graphicsFW.drawPixel(s.getX(), s.getY(), Color.WHITE);
        }
    }
}
