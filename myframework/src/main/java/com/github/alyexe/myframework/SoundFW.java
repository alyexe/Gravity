package com.github.alyexe.myframework;

import android.media.SoundPool;

public class SoundFW {
    private final int mSound;
    private final SoundPool mSoundPool;

    public SoundFW(int sound, SoundPool soundPool) {
        mSound = sound;
        mSoundPool = soundPool;
    }

    public void play(float volume) {
        mSoundPool.play(mSound, volume, volume, 0, 0, 1);
    }

    public void dispose() {
        mSoundPool.unload(mSound);
    }
}
