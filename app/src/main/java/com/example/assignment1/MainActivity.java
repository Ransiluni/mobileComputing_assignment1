package com.example.assignment1;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private Sensor lSensor;
    private SensorManager sensorManager;
    private TextView sensorTextView ;
    String bstart ;
    String bstop ;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void change(View v) {
        bstart = getResources().getString(R.string.btn_start);
        bstop = getResources().getString(R.string.btn_stop);
        start= (Button)findViewById(R.id.btnStart);
        if(start.getText().equals("Start")){

            start.setText(bstop);
            sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
            lSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            this.onStart();
            String sensor_error = getResources().getString(R.string.error_no_sensor);

            sensorTextView = (TextView) findViewById(R.id.sensorReading);

            if (lSensor == null) {
                sensorTextView.setText(sensor_error);
            }else{
                System.out.println("start");
            }
        }else{
            System.out.println("STOP");
            start.setText(bstart);
            sensorTextView = (TextView) findViewById(R.id.sensorReading);
            this.onStop();
            sensorTextView.setText("Sensor offline");
            }

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (lSensor != null) {
            sensorManager.registerListener(this, lSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        float currentValuex = event.values[0];
        float currentValuey = event.values[1];
        float currentValuez = event.values[2];

        sensorTextView = (TextView) findViewById(R.id.sensorReading);
        String output= "Sensor Value x-axis: "+currentValuex+"\nSensor Value y-axis: "+currentValuey+
                "\nSensor Value z-axis: "+currentValuez;
        sensorTextView.setText(output);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }





}
