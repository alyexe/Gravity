package com.github.alyexe.gravity.objects;

import android.graphics.Color;
import com.github.alyexe.gravity.R;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

public class Hud {
    private final CoreFW mCoreFW;
    private final int HUD_HEIGHT = 50;
    private int mPassedDistance;
    private int mCurrentPlayerSpeed;
    private int mCurrentPlayerShields;

    public Hud(CoreFW coreFW) {
        mCoreFW = coreFW;
    }

    public void update(int passedDistance, int currentPlayerSpeed, int currentPlayerShields) {
        mPassedDistance = passedDistance;
        mCurrentPlayerSpeed = currentPlayerSpeed;
        mCurrentPlayerShields = currentPlayerShields;
    }

    public void drawing(GraphicsFW graphicsFW) {
        graphicsFW.drawLine(0, HUD_HEIGHT, graphicsFW.getWidthFrameBuffer(), HUD_HEIGHT, Color.WHITE);
        graphicsFW.drawText(mCoreFW.getString(R.string.txt_hud_passedDistance) + ":" + mPassedDistance, 10, 30, Color.GREEN, 25, null);
        graphicsFW.drawText(mCoreFW.getString(R.string.txt_hud_currentPlayerSpeed) + ":" + mCurrentPlayerSpeed, 350, 30, Color.GREEN, 25, null);
        graphicsFW.drawText(mCoreFW.getString(R.string.txt_hud_currentPlayerShields) + ":" + mCurrentPlayerShields, 650, 30, Color.GREEN, 25, null);
    }

    public int getHudHeight() {
        return HUD_HEIGHT;
    }
}
