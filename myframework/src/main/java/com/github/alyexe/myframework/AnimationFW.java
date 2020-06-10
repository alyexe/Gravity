package com.github.alyexe.myframework;

import android.graphics.Bitmap;
import com.github.alyexe.myframework.utilites.UtilRandomFW;

import java.util.List;

public class AnimationFW {
    private final double mAnimationSpeed;
    private final List<Bitmap> mSprites;
    private int mIndexDelay;
    private int mFramesCount;
    private Bitmap mSprite;

    public AnimationFW(double animationSpeed, List<Bitmap> sprites) {
        mSprite = sprites.get(UtilRandomFW.getGap(0, sprites.size() - 1));
        mAnimationSpeed = animationSpeed;
        mSprites = sprites;
        mFramesCount = 0;
    }

    public void runAnimation() {
        mIndexDelay++;
        if (mIndexDelay > mAnimationSpeed) {
            mIndexDelay = 0;
            nextFrame();
        }
    }

    private void nextFrame() {
        if (mFramesCount < mSprites.size()) {
            mSprite = mSprites.get(mFramesCount);
            mFramesCount++;
        } else mFramesCount = 0;
    }

    public void drawingAnimation(GraphicsFW graphicsFW, int x, int y) {
        graphicsFW.drawTexture(mSprite, x, y);
    }
}
