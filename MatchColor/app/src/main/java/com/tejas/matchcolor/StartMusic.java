package com.tejas.matchcolor;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Tejasvi Kumar on 06-07-2017.
 */
public class StartMusic extends Service {
    MediaPlayer mp;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(StartMusic.this, R.raw.music);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }
}

