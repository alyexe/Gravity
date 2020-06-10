package com.github.alyexe.gravity.classes;

import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

import java.util.ArrayList;

public class AssetLoader {
    public AssetLoader(CoreFW coreFW, GraphicsFW graphicsFW) {
        init(coreFW, graphicsFW);
    }

    private void init(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadPlayerSprite(graphicsFW);
        loadPlayerShieldsOnSprite(graphicsFW);
        loadEnemySprite(graphicsFW);
        loadOther(graphicsFW);
        loadAudio(coreFW);
        loadPowerUps(graphicsFW);
    }

    private void loadPowerUps(GraphicsFW graphicsFW) {
        UtilResource.PowerUpShieldSprite = new ArrayList<>();

        int count = 8;
        UtilResource.PowerUpShieldSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 32, 192, 32, 32));
        UtilResource.PowerUpShieldSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 32, 192, 32, 32));
        UtilResource.PowerUpShieldSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 32, 192, 32, 32));
        UtilResource.PowerUpShieldSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count * 32, 192, 32, 32));
    }

    private void loadPlayerShieldsOnSprite(GraphicsFW graphicsFW) {
        UtilResource.playerShieldsOnSprite = new ArrayList<>();
        UtilResource.playerBoostShieldsOnSprite = new ArrayList<>();

        int count = 0;
        UtilResource.playerShieldsOnSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 128, 64, 64));
        UtilResource.playerShieldsOnSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 128, 64, 64));
        UtilResource.playerShieldsOnSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 128, 64, 64));
        UtilResource.playerShieldsOnSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count * 64, 128, 64, 64));

        count = 0;
        UtilResource.playerBoostShieldsOnSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 192, 64, 64));
        UtilResource.playerBoostShieldsOnSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 192, 64, 64));
        UtilResource.playerBoostShieldsOnSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 192, 64, 64));
        UtilResource.playerBoostShieldsOnSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count * 64, 192, 64, 64));
    }

    private void loadAudio(CoreFW coreFW) {
        UtilResource.gameMusic = coreFW.getAudioFW().newMusic("title.mp3");
        UtilResource.explode = coreFW.getAudioFW().newSound("explode.mp3");
        UtilResource.hit = coreFW.getAudioFW().newSound("hit.mp3");
        UtilResource.touch = coreFW.getAudioFW().newSound("touch.mp3");
    }

    private void loadOther(GraphicsFW graphicsFW) {
        UtilResource.shieldHitEnemy = graphicsFW.newSprite(UtilResource.textureAtlas, 0, 128, 64, 64);
    }

    private void loadEnemySprite(GraphicsFW graphicsFW) {
        UtilResource.enemySprite = new ArrayList<>();

        //Asteroid
        int count = 4;
        UtilResource.enemySprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.enemySprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.enemySprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.enemySprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count * 64, 0, 64, 64));
    }

    private void loadPlayerSprite(GraphicsFW graphicsFW) {
        UtilResource.playerSprite = new ArrayList<>();
        UtilResource.playerBoostSprite = new ArrayList<>();
        UtilResource.playerExplodeSprite = new ArrayList<>();

        //Rocket
        int count = 0;
        UtilResource.playerSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.playerSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.playerSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.playerSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count * 64, 0, 64, 64));

        //Boosted rocket
        count = 0;
        UtilResource.playerBoostSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 64, 64, 64));
        UtilResource.playerBoostSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 64, 64, 64));
        UtilResource.playerBoostSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 64, 64, 64));
        UtilResource.playerBoostSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count * 64, 64, 64, 64));

        //Explode rocket
        count = 4;
        UtilResource.playerExplodeSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 4 * 64, 64, 64));
        UtilResource.playerExplodeSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 4 * 64, 64, 64));
        UtilResource.playerExplodeSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 4 * 64, 64, 64));
        UtilResource.playerExplodeSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count * 64, 4 * 64, 64, 64));
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResource.textureAtlas = graphicsFW.newTexture("texture_atlas.png");
    }
}
