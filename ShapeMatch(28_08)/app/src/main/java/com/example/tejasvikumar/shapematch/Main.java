package com.example.tejasvikumar.shapematch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    Intent intent = new Intent(Main.this,MainActivity.class);
                    startActivity(intent);
                    closeActivity();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();




    }
    public void closeActivity(){
        this.finish();
    }
}
