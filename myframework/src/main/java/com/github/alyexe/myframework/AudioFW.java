package com.github.alyexe.myframework;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;

import java.io.IOException;

public class AudioFW {
    private final AssetManager mAssetManager;
    private final SoundPool mSoundPool;

    public AudioFW(Activity activity) {
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        mAssetManager = activity.getAssets();
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
    }

    public MusicFW newMusic(String fileName) {
        try {
            AssetFileDescriptor assetFileDescriptor = mAssetManager.openFd(fileName);
            return new MusicFW(assetFileDescriptor);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Невозможно загрузить музыку");
        }
    }

    public SoundFW newSound(String filename) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            assetFileDescriptor = mAssetManager.openFd(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int sound = mSoundPool.load(assetFileDescriptor, 0);
        return new SoundFW(sound, mSoundPool);
    }
}
