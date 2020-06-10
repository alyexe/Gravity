package com.github.alyexe.myframework;

import android.graphics.Rect;

public abstract class ObjectFW {
    private int mMaxScreenX;
    private int mMaxScreenY;
    private int mMinScreenX;
    private int mMinScreenY;
    private int mX;
    private int mY;
    private double mSpeed;

    private Rect mHitBox;
    private double mRadius;

    public int getMaxScreenX() {
        return mMaxScreenX;
    }

    public void setMaxScreenX(int maxScreenX) {
        this.mMaxScreenX = maxScreenX;
    }

    public int getMaxScreenY() {
        return mMaxScreenY;
    }

    public void setMaxScreenY(int maxScreenY) {
        this.mMaxScreenY = maxScreenY;
    }

    public int getMinScreenX() {
        return mMinScreenX;
    }

    public void setMinScreenX(int minScreenX) {
        this.mMinScreenX = minScreenX;
    }

    public int getMinScreenY() {
        return mMinScreenY;
    }

    public void setMinScreenY(int minScreenY) {
        this.mMinScreenY = minScreenY;
    }

    public int getX() {
        return mX;
    }

    public void setX(int x) {
        this.mX = x;
    }

    public int getY() {
        return mY;
    }

    public void setY(int y) {
        this.mY = y;
    }

    public double getSpeed() {
        return mSpeed;
    }

    public void setSpeed(double speed) {
        this.mSpeed = speed;
    }

    public Rect getHitBox() {
        return mHitBox;
    }

    public void setHitBox(Rect hitBox) {
        this.mHitBox = hitBox;
    }

    public double getRadius() {
        return mRadius;
    }

    public void setRadius(double radius) {
        this.mRadius = radius;
    }
}
