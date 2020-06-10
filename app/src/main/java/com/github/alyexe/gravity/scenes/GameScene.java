package com.github.alyexe.gravity.scenes;

import android.graphics.Color;
import com.github.alyexe.gravity.R;
import com.github.alyexe.gravity.classes.GameManager;
import com.github.alyexe.gravity.utilites.GameSettings;
import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.SceneFW;

public class GameScene extends SceneFW {

    private GameState mGameState;
    private GameManager mGameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        init(coreFW);
    }

    private void init(CoreFW coreFW) {
        mGameState = GameState.READY;
        mGameManager = new GameManager(coreFW, sceneWidth, sceneHeight);
        UtilResource.gameMusic.play(true, 10f);
    }

    @Override
    public void update() {
        if (mGameState == GameState.READY) {
            updateStateReady();
        }
        if (mGameState == GameState.RUNNING) {
            updateStateRunning();
        }
        if (mGameState == GameState.PAUSE) {
            updateStatePause();
        }
        if (mGameState == GameState.GAME_OVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);

        if (mGameState == GameState.READY) {
            drawingStateReady();
        }
        if (mGameState == GameState.RUNNING) {
            drawingStateRunning();
        }
        if (mGameState == GameState.PAUSE) {
            drawingStatePause();
        }
        if (mGameState == GameState.GAME_OVER) {
            drawingStateGameOver();
        }
    }

    private void drawingStateGameOver() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_distance) + ": " + mGameManager.getPassedDistance(), 250, 200, Color.WHITE, 30, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_gameOver), 250, 300, Color.WHITE, 60, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_restart), 250, 360, Color.WHITE, 30, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_exit), 250, 420, Color.WHITE, 30, null);
    }

    private void updateStateGameOver() {
        GameSettings.addDistance(mGameManager.getPassedDistance());
        if (coreFW.getTouchListenerFW().getTouchUp(250, 360, 100, 35)) {
            coreFW.setSceneFW(new GameScene(coreFW));
        }

        if (coreFW.getTouchListenerFW().getTouchUp(250, 420, 100, 35)) {
            coreFW.setSceneFW(new MainMenuScene(coreFW));
        }
    }

    private void drawingStatePause() {

    }

    private void updateStatePause() {

    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
//        graphicsFW.drawText("Game scene", 250, 300, Color.WHITE, 60, null);
        mGameManager.drawing(graphicsFW);
    }

    private void updateStateRunning() {
        mGameManager.update();
        if (GameManager.gameOver) {
            mGameState = GameState.GAME_OVER;
        }
    }

    private void drawingStateReady() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready), 250, 300, Color.WHITE, 60, null);
    }

    private void updateStateReady() {
        if (coreFW.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            mGameState = GameState.RUNNING;
        }
    }

    @Override
    public void pause() {
        UtilResource.gameMusic.stop();
    }

    @Override
    public void resume() {
        UtilResource.gameMusic.play(true, 10f);
    }

    @Override
    public void dispose() {
        UtilResource.explode.dispose();
        UtilResource.hit.dispose();
        UtilResource.touch.dispose();
        UtilResource.gameMusic.dispose();
    }

    enum GameState {
        READY, RUNNING, PAUSE, GAME_OVER
    }
}
