package com.example.myapp3_01;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MyActivity extends Activity implements SensorEventListener{

    private SensorManager msensorManager;

    private float[] rotationMatrix;
    private float[] accelData;
    private float[] magnetData;
    private float[] OrientationData;

    int[] layouts = {R.id.xz1, R.id.xz2};
    private Random rnd;
    public int rnd() {
        return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    public TextView xzView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        msensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];

        xzView = (TextView) findViewById(R.id.xzValue);
        setContentView(R.layout.main);

        rnd = new Random();
        for (int id : layouts) {
            findViewById(id).setBackgroundColor(rnd());
        }
    }

    @Override
    protected void onResume() {
    	super.onResume();
    	msensorManager.registerListener(this, msensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI );
    	msensorManager.registerListener(this, msensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_UI );
    }

    @Override
    protected void onPause() {
        super.onPause();
        msensorManager.unregisterListener(this);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }

    public void onSensorChanged(SensorEvent event) {
        loadNewSensorData(event);
        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData);
        SensorManager.getOrientation (rotationMatrix, OrientationData);

        if((xzView==null)){
            xzView = (TextView) findViewById(R.id.xzValue);
        }
        xzView.setText(String.valueOf(Math.round(Math.toDegrees(OrientationData[1]))));

        if (xzView !=null){

        }
    }

    private void loadNewSensorData(SensorEvent event) {

        final int type = event.sensor.getType();

        if (type == Sensor.TYPE_ACCELEROMETER) {
            accelData = event.values.clone();
        }

        if (type == Sensor.TYPE_MAGNETIC_FIELD) {
            magnetData = event.values.clone();
        }
    }

}