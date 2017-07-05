package com.example.tejasvikumar.shapematch;

        import android.app.AlertDialog;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.media.AudioManager;
        import android.media.SoundPool;
        import android.os.Bundle;
        import android.os.Vibrator;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_play,bt_setting,bt_hs,bt_about;
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
        bt_setting = (Button) findViewById(R.id.setting);
        bt_hs = (Button) findViewById(R.id.hs);
        bt_about = (Button) findViewById(R.id.about);


        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                Intent intent1 = new Intent(MainActivity.this,Start.class);
                startActivity(intent1);
                closeActivity();
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);
            }
        });
        bt_hs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("How To Play");
                builder.setMessage("This is a simple mind game, In which you have to see the first image  that appears when you click 'PLAY' button and then when You click 'START' button a new image is come on the screen you have to match this image with previous one if image is matched then click 'MATCH' other wise click 'NOT MATCH' for one right click you get 1 score increment, For one time You can play this game only for 59 sec");
                builder.show();
            }
        });

    }
    private void closeActivity(){
        this.finish();
    }
}
