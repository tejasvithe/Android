package com.example.tejasvikumar.shapematch;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ShapeActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView shape;
    SharedPreferences sharedPreferences;
    Button match ,not_match;
    TextView tv_score,tv_timer;
    int n1,n;
    int score1;
    Vibrator vibrator;
    SoundPool soundPool;
    int soundId;
    String score = "myscore";
    String name = "mypreferences";
    AlertDialog.Builder build;
    SharedPreferences.Editor edit;
    int[] res= {R.drawable.rectangle,R.drawable.circle,R.drawable.square,R.drawable.triangle};
    private Random random;

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = new Intent(ShapeActivity.this,StartMusic.class);
        startService(i);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(ShapeActivity.this, R.raw.tone, 1);



        Intent intent = getIntent();
        n1 = intent.getExtras().getInt("user");
        random =new Random();
        tv_score = (TextView) findViewById(R.id.score);
        tv_timer = (TextView) findViewById(R.id.timer);
        match = (Button) findViewById(R.id.match);
        not_match = (Button) findViewById(R.id.notmatch);

        sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        edit = sharedPreferences.edit();


        match.setOnClickListener(this);
        not_match.setOnClickListener(this);

        shape = (ImageView) findViewById(R.id.shape);
        new CountDownTimer(59000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText(""+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder build = new AlertDialog.Builder(ShapeActivity.this);
                build.setTitle("Game Over!");
                build.setMessage("Score=" + tv_score.getText());
                build.setCancelable(false);

                sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
                String data = sharedPreferences.getString("myscore"," ");
                if(data != " ") {
                    int int_data = Integer.parseInt(data);
                    int int_score = Integer.parseInt(tv_score.getText().toString().trim());

                    if (int_data <= int_score) {
                        score1 = int_score;
                    } else
                        score1 = int_data;
                    edit.remove("myscore");
                    edit.putString("myscore", "" + score1);
                    edit.commit();
                }
                else
                    edit.putString("myscore", "" + Integer.parseInt(tv_score.getText().toString().trim()));
                edit.commit();


                build.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(ShapeActivity.this, Start.class);
                        startActivity(i);
                        closeActivity();
                    }
                });
                build.setNegativeButton("exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(ShapeActivity.this, MainActivity.class);
                        startActivity(i);
                        closeActivity();
                    }
                });

                tv_timer.setText("done!");
                build.show();

            }
        }.start();

        n = random.nextInt(4)+0;
        shape.setBackgroundResource(res[n]);

    }

    @Override
    protected void onStop() {
        super.onStop();
        soundPool.release();
        Intent i = new Intent(ShapeActivity.this,StartMusic.class);
        stopService(i);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.match:
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                soundPool.release();
                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                if (n1 == n)
                {
                    int n2 = Integer.parseInt(tv_score.getText().toString());
                    n2 = n2 + 1;
                    tv_score.setText("" + n2);


                }
                else
                {
                    AlertDialog.Builder build = new AlertDialog.Builder(ShapeActivity.this);
                    build.setTitle("Game Over!");
                    build.setMessage("Score=" + tv_score.getText());
                    build.setCancelable(false);

                    sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
                    String data = sharedPreferences.getString("myscore"," ");
                    if(data != " ") {
                        int int_data = Integer.parseInt(data);
                        int int_score = Integer.parseInt(tv_score.getText().toString().trim());

                        if (int_data <= int_score) {
                            score1 = int_score;
                        } else
                            score1 = int_data;
                        edit.remove("myscore");
                        edit.putString("myscore", "" + score1);
                        edit.commit();
                    }
                    else
                        edit.putString("myscore", "" + Integer.parseInt(tv_score.getText().toString().trim()));
                    edit.commit();


                    build.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ShapeActivity.this, Start.class);
                            startActivity(i);
                            closeActivity();
                        }
                    });
                    build.setNegativeButton("exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ShapeActivity.this, MainActivity.class);
                            startActivity(i);
                            closeActivity();
                        }
                    });
                    build.show();


                    Toast.makeText(ShapeActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                n1 = n;
                n = random.nextInt(4) + 0;
                shape.setBackgroundResource(res[n]);
                break;


            case R.id.notmatch:
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                soundPool.release();
                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);
                if (n1 != n)
                {
                    int n2 = Integer.parseInt(tv_score.getText().toString());
                    n2++;
                    tv_score.setText("" + n2);

                }
                else
                {
                    AlertDialog.Builder build = new AlertDialog.Builder(ShapeActivity.this);
                    build.setTitle("Game Over!");
                    build.setMessage("Score=" + tv_score.getText());
                    build.setCancelable(false);

                    sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
                    String data = sharedPreferences.getString("myscore"," ");
                    if(data != " ") {
                        int int_data = Integer.parseInt(data);
                        int int_score = Integer.parseInt(tv_score.getText().toString().trim());

                        if (int_data <= int_score) {
                            score1 = int_score;
                        } else
                            score1 = int_data;
                        edit.remove("myscore");
                        edit.putString("myscore", "" + score1);
                        edit.commit();
                    }
                    else
                        edit.putString("myscore", "" + Integer.parseInt(tv_score.getText().toString().trim()));
                    edit.commit();

                    build.setPositiveButton("continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ShapeActivity.this, Start.class);
                            startActivity(i);
                            closeActivity();
                        }
                    });
                    build.setNegativeButton("exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(ShapeActivity.this, MainActivity.class);
                            startActivity(i);
                            closeActivity();
                        }
                    });
                    build.show();


                    Toast.makeText(ShapeActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                n1 = n;
                n = random.nextInt(4) + 0;
                shape.setBackgroundResource(res[n]);
                break;
        }

    }
    private void closeActivity(){
        this.finish();
    }


}
