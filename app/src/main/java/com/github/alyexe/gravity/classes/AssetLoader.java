package com.github.alyexe.gravity.classes;

import com.github.alyexe.gravity.utilites.UtilResource;
import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;

import java.util.ArrayList;

public class AssetLoader {
    public AssetLoader(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpriteLayer(graphicsFW);
        loadEnemySprite(graphicsFW);
        loadOther(graphicsFW);
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
        UtilResource.enemySprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
    }

    private void loadSpriteLayer(GraphicsFW graphicsFW) {
        UtilResource.playerSprite = new ArrayList<>();
        UtilResource.playerBoostSprite = new ArrayList<>();
        UtilResource.playerExplodeSprite = new ArrayList<>();

        //Rocket
        int count = 0;
        UtilResource.playerSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.playerSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.playerSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));
        UtilResource.playerSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 0, 64, 64));

        //Boosted rocket
        count = 0;
        UtilResource.playerBoostSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 64, 64, 64));
        UtilResource.playerBoostSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 64, 64, 64));
        UtilResource.playerBoostSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 64, 64, 64));
        UtilResource.playerBoostSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 64, 64, 64));

        //Explode rocket
        count = 4;
        UtilResource.playerExplodeSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 4 * 64, 64, 64));
        UtilResource.playerExplodeSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 4 * 64, 64, 64));
        UtilResource.playerExplodeSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 4 * 64, 64, 64));
        UtilResource.playerExplodeSprite.add(graphicsFW.newSprite(UtilResource.textureAtlas, count++ * 64, 4 * 64, 64, 64));
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResource.textureAtlas = graphicsFW.newTexture("texture_atlas.png");
    }
}
