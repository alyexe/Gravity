package com.github.alyexe.gravity.classes;

import com.github.alyexe.myframework.CoreFW;
import com.github.alyexe.myframework.GraphicsFW;
import com.github.alyexe.myframework.utilites.UtilResourceFW;

public class AssetLoader {
    public AssetLoader(CoreFW coreFW, GraphicsFW graphicsFW) {
        loadTexture(graphicsFW);
        loadSpriteLayer(graphicsFW);
    }

    private void loadSpriteLayer(GraphicsFW graphicsFW) {
        UtilResourceFW.playerSprite.add(graphicsFW.newSprite(UtilResourceFW.textureAtlas, 0, 0, 64, 64));
        UtilResourceFW.playerSprite.add(graphicsFW.newSprite(UtilResourceFW.textureAtlas, 64, 0, 64, 64));
        UtilResourceFW.playerSprite.add(graphicsFW.newSprite(UtilResourceFW.textureAtlas, 128, 0, 64, 64));
        UtilResourceFW.playerSprite.add(graphicsFW.newSprite(UtilResourceFW.textureAtlas, 192, 0, 64, 64));
    }

    private void loadTexture(GraphicsFW graphicsFW) {
        UtilResourceFW.textureAtlas = graphicsFW.newTexture("texture_atlas.png");
    }
}
