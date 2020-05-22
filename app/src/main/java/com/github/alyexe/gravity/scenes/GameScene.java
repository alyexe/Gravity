package com.github.alyexe.gravity.scenes;

import android.graphics.Color;
import com.github.alyexe.gravity.R;
import com.github.alyexe.gravity.classes.GameManager;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.SceneFW;
import com.github.alyexe.myframework.utilites.GameSettings;

public class GameScene extends SceneFW {

    GameState gameState;
    GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;
        gameManager = new GameManager(coreFW, sceneWidth, sceneHeight);
    }

    @Override
    public void update() {
        if (gameState == GameState.READY) {
            updateStateReady();
        }
        if (gameState == GameState.RUNNING) {
            updateStateRunning();
        }
        if (gameState == GameState.PAUSE) {
            updateStatePause();
        }
        if (gameState == GameState.GAME_OVER) {
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);

        if (gameState == GameState.READY) {
            drawingStateReady();
        }
        if (gameState == GameState.RUNNING) {
            drawingStateRunning();
        }
        if (gameState == GameState.PAUSE) {
            drawingStatePause();
        }
        if (gameState == GameState.GAME_OVER) {
            drawingStateGameOver();
        }
    }

    private void drawingStateGameOver() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_distance) + ": " + gameManager.getPassedDistance(), 250, 200, Color.WHITE, 30, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_gameOver), 250, 300, Color.WHITE, 60, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_restart), 250, 360, Color.WHITE, 30, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateGameOver_exit), 250, 420, Color.WHITE, 30, null);
    }

    private void updateStateGameOver() {
        GameSettings.addDistance(gameManager.getPassedDistance());
        if (coreFW.getTouchListenerFW().getTouchUp(250, 360, 100, 35)) {
            coreFW.setSceneFW(new GameScene(coreFW));
        }

        if (coreFW.getTouchListenerFW().getTouchUp(250, 420, 100, 35)) {
            coreFW.setSceneFW(new MainMenuScene(coreFW));
        }    }

    private void drawingStatePause() {

    }

    private void updateStatePause() {

    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
//        graphicsFW.drawText("Game scene", 250, 300, Color.WHITE, 60, null);
        gameManager.drawing(coreFW, graphicsFW);
    }

    private void updateStateRunning() {
        gameManager.update();
        if (GameManager.gameOver) {
            gameState = GameState.GAME_OVER;
        }
    }

    private void drawingStateReady() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready), 250, 300, Color.WHITE, 60, null);
    }

    private void updateStateReady() {
        if (coreFW.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)) {
            gameState = GameState.RUNNING;
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    enum GameState {
        READY, RUNNING, PAUSE, GAME_OVER
    }
}
