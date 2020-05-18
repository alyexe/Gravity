package com.github.alyexe.gravity.scenes;

import android.graphics.Color;
import com.github.alyexe.gravity.R;
import com.github.alyexe.gravity.classes.GameManager;
import com.github.alyexe.gravity.generators.BackgroundGenerator;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.SceneFW;

public class GameScene extends SceneFW {

    enum GameState {
        READY, RUNNING, PAUSE, GAMEOVER
    }

    GameState gameState;
    BackgroundGenerator backgroundGenerator;
    GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;
        backgroundGenerator = new BackgroundGenerator(sceneWidth, sceneHeight);
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
        if (gameState == GameState.GAMEOVER) {
            updateStateGameover();
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
        if (gameState == GameState.GAMEOVER) {
            drawingStateGameover();
        }
    }

    private void drawingStateGameover() {

    }

    private void updateStateGameover() {

    }

    private void drawingStatePause() {

    }

    private void updateStatePause() {

    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText("Game scene", 250, 300, Color.WHITE, 60, null);
        backgroundGenerator.drawing(graphicsFW);
        gameManager.drawing(coreFW, graphicsFW);
    }

    private void updateStateRunning() {
        backgroundGenerator.update();
        gameManager.update();
    }

    private void drawingStateReady() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready),
                250, 300, Color.WHITE, 60, null);
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
}
