package com.tejas.matchcolor;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_play,bt_hs,bt_about;
    SoundPool soundPool;
    int soundId;
    SharedPreferences sharedPreferences;
    String score = "myscore";
    String name = "mypreferences";

    @Override
    protected void onStop() {
        super.onStop();
        soundPool.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(MainActivity.this, R.raw.tone, 1);

        bt_play = (Button) findViewById(R.id.play);
        bt_hs = (Button) findViewById(R.id.hs);
        bt_about = (Button) findViewById(R.id.about);


        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                Intent intent1 = new Intent(MainActivity.this,ShapeActivity.class);
                startActivity(intent1);
                closeActivity();
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);
            }
        });
        bt_hs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
                String score1 = sharedPreferences.getString(score,"");
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("HIGH SCORE");
                builder.setMessage(score1);
                builder.show();
            }
        });
        bt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("How To Play");
                builder.setMessage("This is a simple colour matching game ,In which when you click the 'PLAY' Button one screen is appears in which one Text is present with some specific colour that text is actually a colour name you have to check that the name is same as the colour or not if yes then you have to click 'MATCH' button otherwise 'NOT MATCH' for correct answer you will get 1 score increment,you have to click the right button within the time period otherwise game will ends.");
                builder.show();
            }
        });

    }
    private void closeActivity(){
        this.finish();
    }
    }

