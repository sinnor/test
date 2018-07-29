package com.example.sinnor.test;

import android.content.Context;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEvent;
import android.util.Log;
import android.view.View;
import android.widget.ToggleButton;

public class SensorTestActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Context mContext = SensorTestActivity.this;
    private String TAG = SensorTestActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_test);
        ToggleButton mtb = (ToggleButton)findViewById(R.id.view_btn) ;
        mtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean on = ((ToggleButton) v).isChecked();
                if (on) {
                    //do on
                }
                else{
                    //do off
                }

            }
        });
        mSensorManager = (SensorManager)mContext.getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (mSensor == null) {
            Log.d(TAG, "Sensor not exist!");
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListen, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private SensorEventListener mSensorListen = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float mdata = event.values[0];
            final long now = event.timestamp;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    protected void onStop() {
        mSensorManager.unregisterListener(mSensorListen);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
