package com.github.alyexe.myframework;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ViewConstructor")
public class LoopFW extends SurfaceView implements Runnable {

    private final float FPS = 60;
    private final float SECOND = 1000000000;
    private final float UPDATE_TIME = SECOND / FPS;
    private final CoreFW mCoreFW;
    private final Bitmap mFrameBuffer;
    private final SurfaceHolder mSurfaceHolder;
    private final Rect mRect;
    private boolean mRunning = false;
    private Thread mGameThread = null;
    private Canvas mCanvas;

    public LoopFW(CoreFW coreFW, Bitmap frameBuffer) {
        super(coreFW);
        mCoreFW = coreFW;
        mFrameBuffer = frameBuffer;
        mSurfaceHolder = getHolder();
        mRect = new Rect();
        mCanvas = new Canvas();
    }

    @Override
    public void run() {
        float lastTime = System.nanoTime();
        float delta = 0;

        while (mRunning) {
            float nowTime = System.nanoTime();
            float elapsedTime = nowTime - lastTime;
            lastTime = nowTime;
            delta += elapsedTime / UPDATE_TIME;
            if (delta > 1) {
                updateGame();
                drawingGame();
                delta--;
            }
        }
    }

    public void startGame() {
        if (mRunning) {
            return;
        }

        mRunning = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    private void updateGame() {
        mCoreFW.getCurrentScene().update();
    }

    private void drawingGame() {
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.getClipBounds(mRect);
            mCanvas.drawBitmap(mFrameBuffer, null, mRect, null);
            mCoreFW.getCurrentScene().drawing();
            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    public void stopGame() {
        if (!mRunning) {
            return;
        }
        mRunning = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
