package com.github.alyexe.myframework;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity {

    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;
    private final String SETTINGS = "settings";

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;
    private AudioFW audioFW;
    private Display display;
    private Point displaySize;
    private Bitmap frameBuffer;
    private SceneFW sceneFW;
    private float sceneWidth;
    private float sceneHeight;
    private boolean stateOnPause;
    private boolean stateOnResume;
    private SharedPreferences sharedPreferences;

    public CoreFW() {
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        displaySize = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);

        frameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);
        sceneWidth = FRAME_BUFFER_WIDTH / displaySize.x;
        sceneHeight = FRAME_BUFFER_HEIGHT / displaySize.y;
        audioFW = new AudioFW(this);

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        touchListenerFW = new TouchListenerFW(loopFW, sceneWidth, sceneHeight);

        sceneFW = getStartScene();

        stateOnPause = false;
        stateOnResume = false;
        setContentView(loopFW);
    }

    @Override
    public void onResume() {
        super.onResume();
        sceneFW.resume();
        loopFW.startGame();
    }

    @Override
    public void onPause() {
        super.onPause();
        sceneFW.pause();
        loopFW.stopGame();
        stateOnPause = true;
        if (isFinishing()) {
            sceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW() {
        return graphicsFW;
    }

    public TouchListenerFW getTouchListenerFW() {
        return touchListenerFW;
    }

    public void setSceneFW(SceneFW sceneFW) {
        if (sceneFW == null) {
            throw new IllegalArgumentException("Scene must not be NULL!");
        }
        this.sceneFW.pause();
        this.sceneFW.dispose();
        sceneFW.resume();
        sceneFW.update();
        this.sceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return sceneFW;
    }

    public SceneFW getStartScene() {
        return sceneFW;
    }

    public AudioFW getAudioFW() {
        return audioFW;
    }
}
