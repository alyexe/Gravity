package com.github.alyexe.myframework;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MusicFW implements MediaPlayer.OnCompletionListener {

    private final MediaPlayer mMediaPlayer;
    private boolean isPrepared = false;

    public MusicFW(AssetFileDescriptor assetFileDescriptor) {
        mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
            mMediaPlayer.prepare();
            isPrepared = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        mMediaPlayer.setOnCompletionListener(this);
    }

    public void play(boolean looping, float volume) {
        if (mMediaPlayer.isPlaying()) {
            return;
        }

        synchronized (this) {
            if (!isPrepared) {
                try {
                    mMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            mMediaPlayer.setLooping(looping);
            mMediaPlayer.setVolume(volume, volume);
            mMediaPlayer.start();
        }
    }

    public void stop() {
        mMediaPlayer.stop();
        synchronized (this) {
            isPrepared = false;
        }
    }

    public void dispose() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        synchronized (this) {
            isPrepared = false;
        }
    }
}
