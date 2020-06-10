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

    private LoopFW mLoopFW;
    private GraphicsFW mGraphicsFW;
    private TouchListenerFW mTouchListenerFW;
    private AudioFW mAudioFW;
    private SceneFW mSceneFW;
    private SharedPreferences mSharedPreferences;

    public CoreFW() {
    }

    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences(SETTINGS, MODE_PRIVATE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Point displaySize = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(displaySize);

        Bitmap frameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);
        float sceneWidth = FRAME_BUFFER_WIDTH / displaySize.x;
        float sceneHeight = FRAME_BUFFER_HEIGHT / displaySize.y;
        mAudioFW = new AudioFW(this);
        mLoopFW = new LoopFW(this, frameBuffer);
        mGraphicsFW = new GraphicsFW(getAssets(), frameBuffer);
        mTouchListenerFW = new TouchListenerFW(mLoopFW, sceneWidth, sceneHeight);
        mSceneFW = getStartScene();

        setContentView(mLoopFW);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSceneFW.resume();
        mLoopFW.startGame();
    }

    @Override
    public void onPause() {
        super.onPause();
        mSceneFW.pause();
        mLoopFW.stopGame();
        if (isFinishing()) {
            mSceneFW.dispose();
        }
    }

    public GraphicsFW getGraphicsFW() {
        return mGraphicsFW;
    }

    public TouchListenerFW getTouchListenerFW() {
        return mTouchListenerFW;
    }

    public void setSceneFW(SceneFW sceneFW) {
        if (sceneFW == null) {
            throw new IllegalArgumentException("Scene must not be NULL!");
        }
        this.mSceneFW.pause();
        this.mSceneFW.dispose();
        sceneFW.resume();
        sceneFW.update();
        this.mSceneFW = sceneFW;
    }

    public SceneFW getCurrentScene() {
        return mSceneFW;
    }

    public SceneFW getStartScene() {
        return mSceneFW;
    }

    public AudioFW getAudioFW() {
        return mAudioFW;
    }
}
