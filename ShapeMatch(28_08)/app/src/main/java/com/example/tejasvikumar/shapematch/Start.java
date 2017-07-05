package com.example.tejasvikumar.shapematch;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class Start extends AppCompatActivity {
    Button bt_start;
    ImageView iv_shape;
    SoundPool soundPool;
    int soundId;
    int[] res= {R.drawable.rectangle,R.drawable.circle,R.drawable.square,R.drawable.triangle};
    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(Start.this, R.raw.tone, 1);

        random = new Random();
        iv_shape = (ImageView) findViewById(R.id.shape);
        bt_start = (Button) findViewById(R.id.start);
        final int n = random.nextInt(4)+0;
        iv_shape.setBackgroundResource(res[n]);
        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                Intent intent = new Intent(Start.this,ShapeActivity.class);
                intent.putExtra("user", n);
                startActivity(intent);
                closeActivity();
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);

            }
        });


    }
    public void closeActivity(){
        this.finish();
    }
}
