package com.github.alyexe.myframework;

import android.content.res.AssetManager;
import android.graphics.*;

import java.io.IOException;
import java.io.InputStream;

public class GraphicsFW {

    private final AssetManager mAssetManagerGame;
    private final Bitmap mFrameBufferGame;
    private final Canvas mCanvasGame;
    private final Paint mPaintGame;
    private Bitmap mTextureGame;

    public GraphicsFW(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        mAssetManagerGame = assetManagerGame;
        mFrameBufferGame = frameBufferGame;
        mCanvasGame = new Canvas(frameBufferGame);
        mPaintGame = new Paint();
    }

    public void clearScene(int colorRGB) {
        mCanvasGame.drawRGB(colorRGB, colorRGB, colorRGB);
    }

    public void drawPixel(int x, int y, int color) {
        mPaintGame.setColor(color);
        mCanvasGame.drawPoint(x, y, mPaintGame);
    }

    public void drawLine(int startX, int startY, int stopX, int stopY, int color) {
        mPaintGame.setColor(color);
        mCanvasGame.drawLine(startX, startY, stopX, stopY, mPaintGame);
    }

    public void drawText(String text, int x, int y, int color, int size, Typeface font) {
        mPaintGame.setColor(color);
        mPaintGame.setTextSize(size);
        mPaintGame.setTypeface(font);
        mCanvasGame.drawText(text, x, y, mPaintGame);
    }

    public void drawTexture(Bitmap texture, int x, int y) {
        mCanvasGame.drawBitmap(texture, x, y, null);
    }

    public int getWidthFrameBuffer() {
        return mFrameBufferGame.getWidth();
    }

    public int getHeightFrameBuffer() {
        return mFrameBufferGame.getHeight();
    }

    public Bitmap newTexture(String fileName) {
        InputStream inputStream = null;
        try {
            inputStream = mAssetManagerGame.open(fileName);
            mTextureGame = BitmapFactory.decodeStream(inputStream);
            if (mTextureGame == null) {
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
        return mTextureGame;
    }

    public Bitmap newSprite(Bitmap textureAtlas, int x, int y, int spriteWidth, int spriteHeight) {
        return Bitmap.createBitmap(textureAtlas, x, y, spriteWidth, spriteHeight);
    }
}
