package com.github.alyexe.myframework;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

public class TouchListenerFW implements View.OnTouchListener {

    private final float mSceneWidth;
    private final float mSceneHeight;
    private float mTouchX;
    private float mTouchY;
    private boolean mIsTouchDown;
    private boolean mIsTouchUp;

    public TouchListenerFW(View view, float sceneWidth, float sceneHeight) {
        view.setOnTouchListener(this);
        mSceneWidth = sceneWidth;
        mSceneHeight = sceneHeight;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View view, MotionEvent event) {

        synchronized (this) {
            mIsTouchDown = false;
            mIsTouchUp = false;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mTouchX = event.getX() * mSceneWidth;
                    mTouchY = event.getY() * mSceneHeight;
                    mIsTouchDown = true;
                    mIsTouchUp = false;
                    break;
                case MotionEvent.ACTION_UP:
                    mTouchX = event.getX() * mSceneWidth;
                    mTouchY = event.getY() * mSceneHeight;
                    mIsTouchDown = false;
                    mIsTouchUp = true;
                    break;
            }
        }
        return true;
    }

    public boolean getTouchUp(int x, int y, int touchWidth, int touchHeight) {
        if (mIsTouchUp) {
            if (mTouchX >= x && mTouchX <= x + touchWidth - 1 && mTouchY <= y && mTouchY >= y - touchHeight - 1) {
                mIsTouchUp = false;
                return true;
            }
        }
        return false;
    }

    public boolean getTouchDown(int x, int y, int touchWidth, int touchHeight) {
        if (mIsTouchDown) {
            if (mTouchX >= x && mTouchX <= x + touchWidth - 1 && mTouchY <= y && mTouchY >= y - touchHeight - 1) {
                mIsTouchDown = false;
                return true;
            }
        }
        return false;
    }
}
