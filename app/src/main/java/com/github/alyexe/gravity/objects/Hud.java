package com.github.alyexe.gravity.objects;

import android.graphics.Color;
import com.github.alyexe.gravity.R;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

public class Hud {
    private int passedDistance;
    private int currentPlayerSpeed;
    private int currentPlayerShields;

    CoreFW coreFW;

    private final int HUD_HEIGHT = 50;

    public Hud(CoreFW coreFW) {
        this.coreFW = coreFW;
    }

    public void update(int passedDistance, int currentPlayerSpeed, int currentPlayerShields) {
        this.passedDistance = passedDistance;
        this.currentPlayerSpeed = currentPlayerSpeed;
        this.currentPlayerShields = currentPlayerShields;
    }

    public void drawing(GraphicsFW graphicsFW) {
        graphicsFW.drawLine(0, HUD_HEIGHT, graphicsFW.getWidthFrameBuffer(), HUD_HEIGHT, Color.WHITE);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_passedDistance) + ":" + passedDistance, 10, 30, Color.GREEN, 25, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentPlayerSpeed) + ":" + currentPlayerSpeed, 350, 30, Color.GREEN, 25, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_hud_currentPlayerShields) + ":" + currentPlayerShields, 650, 30, Color.GREEN, 25, null);
    }

    public int getHUD_HEIGHT() {
        return HUD_HEIGHT;
    }
}
