package com.github.alyexe.gravity.classes;

import android.graphics.Bitmap;
import com.github.alyexe.myframework.GraphicsFW;

import java.util.List;

public class GameAnimation {
    private double animationSpeed;
    private int indexDelay;
    private int framesCount;
//    private int frames;

    private Bitmap sprite;
    private List<Bitmap> sprites;

    public GameAnimation(double animationSpeed, List<Bitmap> sprites) {
        this.sprite = sprites.get(0);
        this.animationSpeed = animationSpeed;
        this.sprites = sprites;
        this.framesCount = 0;
//        frames = 4;
    }

    public void runAnimation() {
        indexDelay++;
        if (framesCount < sprites.size()) {
            sprite = sprites.get(framesCount);
            framesCount++;
        }
    }

    public void drawingAnimation(GraphicsFW graphicsFW, int x, int y) {
        graphicsFW.drawTexture(sprite, x, y);
    }
}

//TODO Этот класс можно перенести в фпеймворк. Подумать как.