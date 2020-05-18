package com.github.alyexe.myframework;

import android.content.res.AssetManager;
import android.graphics.*;

import java.io.IOException;
import java.io.InputStream;

public class GraphicsFW {

    private AssetManager assetManagerGame;
    private Bitmap frameBufferGame;
    private Canvas canvasGame;
    private Paint paintGame;
    private Bitmap textureGame;

    public GraphicsFW(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        this.assetManagerGame = assetManagerGame;
        this.frameBufferGame = frameBufferGame;
        this.canvasGame = new Canvas(frameBufferGame);
        this.paintGame = new Paint();
    }

    public void clearScene(int colorRGB) {
        canvasGame.drawRGB(colorRGB, colorRGB, colorRGB);
    }

    public void drawPixel(int x, int y, int color) {
        paintGame.setColor(color);
        canvasGame.drawPoint(x, y, paintGame);
    }

    public void drawLine(int startX, int startY, int stopX, int stopY, int color) {
        paintGame.setColor(color);
        canvasGame.drawLine(startX, startY, stopX, stopY, paintGame);
    }

    public void drawText(String text, int x, int y, int color, int size, Typeface font) {
        paintGame.setColor(color);
        paintGame.setTextSize(size);
        paintGame.setTypeface(font);
        canvasGame.drawText(text, x, y, paintGame);
    }

    public void drawTexture(Bitmap texture, int x, int y) {
        canvasGame.drawBitmap(texture, x, y, null);
    }

    public int getWidthFrameBuffer() {
        return frameBufferGame.getWidth();
    }

    public int getHeightFrameBuffer() {
        return frameBufferGame.getHeight();
    }

    public Bitmap newTexture(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = assetManagerGame.open(fileName);
            textureGame = BitmapFactory.decodeStream(inputStream);
            if (textureGame == null) {
                throw new RuntimeException("Error loading file " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return textureGame;
    }

    public Bitmap newSprite(Bitmap textureAtlas, int x, int y, int spriteWidth, int spriteHeight) {
        Bitmap newSprite = Bitmap.createBitmap(textureAtlas, x, y, spriteWidth, spriteHeight);
        return newSprite;
    }
}
