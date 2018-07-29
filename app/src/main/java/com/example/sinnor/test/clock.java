package com.example.sinnor.test;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class clock extends AppCompatActivity {
    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;
    private final String TAG = "CLOCK";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        final Button btn_start = (Button)findViewById(R.id.start);
        final Button btn_pause = (Button)findViewById(R.id.pause);
        final Button btn_reset = (Button)findViewById(R.id.reset);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
            }
        });

        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasrunning");

        }
        runTimer();

    }

    private void runTimer(){
        final TextView timeView = (TextView)findViewById(R.id.time_view);
        final Handler mhandle = new Handler();
        mhandle.post(new Runnable(){
            @Override
            public void run(){
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                seconds = seconds%60;
                String time = String.format("%d:%02d:02d", hours, minutes, seconds);

                timeView.setText(time);
                if(running){
                   seconds++;
                }
                Log.d(TAG, "thread id:" + Thread.currentThread().getId() + " threadname :" + Thread.currentThread().getName());
                mhandle.postDelayed(this, 1000);
                Log.d(TAG, "postDelayed thread id:" + Thread.currentThread().getId() + " threadname :" + Thread.currentThread().getName());
            }

        } );

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected  void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasrunning", wasRunning);
    }
}
