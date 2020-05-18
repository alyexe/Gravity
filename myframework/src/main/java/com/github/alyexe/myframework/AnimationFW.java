package com.github.alyexe.myframework;

import android.graphics.Bitmap;

import java.util.List;

public class AnimationFW {
    private double animationSpeed;
    private int indexDelay;
    private int framesCount;
//    private int frames;

    private Bitmap sprite;
    private List<Bitmap> sprites;

    public AnimationFW(double animationSpeed, List<Bitmap> sprites) {
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
        } else framesCount = 0;
    }

    public void drawingAnimation(GraphicsFW graphicsFW, int x, int y) {
        graphicsFW.drawTexture(sprite, x, y);
    }
}
