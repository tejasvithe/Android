package com.tejas.matchcolor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ShapeActivity extends AppCompatActivity implements View.OnClickListener, ProgressBarAnimation.ICallback {
    TextView shape;
    int size_arr;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    Button match ,not_match;
    TextView tv_score;
    int colour_code_index,colour_index;
    int score1;
    Vibrator vibrator;
    SoundPool soundPool;
    int soundId;
    String name = "mypreferences";
    SharedPreferences.Editor edit;
    public String colour[] = {"RED","BLUE","GREEN","YELLOW","ORANGE","BROWN"};
    public String colour_code[] = {"#D50000","#2196F3","#4CAF50","#FFEB3B","#FF9800","#795548"};
    private Random random;
    private ProgressBarAnimation anim;
    WorkingClass obj =  new WorkingClass();
    Calculate_Percentage obj2=  new Calculate_Percentage();

    int arr[];
    @Override
    public void onProgressFinished() {
        createDialog();
    }

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

        tv_score = (TextView) findViewById(R.id.score);
        match = (Button) findViewById(R.id.match);
        not_match = (Button) findViewById(R.id.notmatch);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        shape = (TextView) findViewById(R.id.shape);

        sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        edit = sharedPreferences.edit();

        random =new Random();

        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundId = soundPool.load(ShapeActivity.this, R.raw.tone, 1);

        match.setOnClickListener(this);
        not_match.setOnClickListener(this);


         arr = obj.generate_Random_No();
        while(obj2.check_Percantage(arr) <= 40)
        {
            arr = obj.generate_Random_No();
        }
        size_arr = arr.length;

       starting_method();


                shape.setText(colour[colour_index]);
                shape.setTextColor(Color.parseColor(colour_code[colour_code_index]));

        anim = new ProgressBarAnimation(this,progressBar, 0, 100);

        anim.setDuration(4000);
        progressBar.startAnimation(anim);
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

                if (colour_code_index == colour_index)
                {
                    int current_score = Integer.parseInt(tv_score.getText().toString());
                    current_score = current_score + 1;
                    tv_score.setText("" + current_score);

                    set_Progress_Duration(current_score);

                    progressBar.setProgress(0);
                    progressBar.clearAnimation();
                    progressBar.startAnimation(anim);
                }
                else
                {
                   createDialog();
                    Toast.makeText(ShapeActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }

                starting_method();


                shape.setText(colour[colour_index]);
                shape.setTextColor(Color.parseColor(colour_code[colour_code_index]));
                break;


            case R.id.notmatch:
                soundPool.play(soundId, 1, 1, 0, 0, 1);
                soundPool.release();
                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(100);

                if (colour_code_index != colour_index)
                {
                    int current_score = Integer.parseInt(tv_score.getText().toString());
                    current_score++;
                    tv_score.setText("" + current_score);

                    set_Progress_Duration(current_score);

                    progressBar.setProgress(0);
                    progressBar.clearAnimation();
                    progressBar.startAnimation(anim);

                }
                else
                {
                    createDialog();

                    Toast.makeText(ShapeActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                }
                starting_method();

                shape.setText(colour[colour_index]);
                shape.setTextColor(Color.parseColor(colour_code[colour_code_index]));
                break;
        }
    }

    private void createDialog() {

        progressBar.setProgress(0);
        progressBar.clearAnimation();

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
                Intent i = new Intent(ShapeActivity.this, ShapeActivity.class);
                startActivity(i);
                closeActivity();
            }
        });
        build.setNegativeButton("exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(ShapeActivity.this, MainActivity.class);
               finish();
                startActivity(i);
            }
        });
        build.show();
    }

    private void closeActivity(){
        this.finish();
    }

    public void starting_method()
    {
        if(size_arr == 0)
        {
            arr = obj.generate_Random_No();
            size_arr = arr.length;
        }

        if(arr[size_arr - 1] == 1)
        {
            colour_index = colour_code_index = random.nextInt(6)+0;
        }
        else {

            colour_index = random.nextInt(6) + 0;
            colour_code_index = random.nextInt(6) + 0;

            while(colour_code_index == colour_index)
            {
                colour_code_index = random.nextInt(6) + 0;
            }
        }
        size_arr--;
    }

    private void set_Progress_Duration(int score)
    {
        int progress_Bar_Max = 0;
        if(score %10 == 0)
        {
           progress_Bar_Max = 4000 - 10 ;
        }

        if(progress_Bar_Max >= 1000)
        {
            anim.setDuration(progress_Bar_Max);
        }
    }

}

