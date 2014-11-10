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
import android.widget.Toast;

import java.util.Random;

public class MyActivity extends Activity implements SensorEventListener{

    private SensorManager msensorManager;

    private float[] rotationMatrix;
    private float[] accelData;
    private float[] magnetData;
    private float[] OrientationData;

    int layout1 = R.id.layout1;
    int layout2 = R.id.layout2;

    private Random rnd;
    public int rnd() {
        return  Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
    TextView xyView2, xzView2,zyView2;
    int rgb;
    int xy2,xz2,zy2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        msensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE); // Получаем менеджер сенсоров
        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];
        rnd = new Random();
        rgb = rnd();

        xyView2 = (TextView) findViewById(R.id.xyValue2);
        xzView2 = (TextView) findViewById(R.id.xzValue2);
        zyView2 = (TextView) findViewById(R.id.zyValue2);

        findViewById(layout1).setBackgroundColor(rgb);

    }

    public void onSensorChanged(SensorEvent event) {
        loadNewSensorData(event);
        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData);
        SensorManager.getOrientation(rotationMatrix, OrientationData);

        xy2 = (int) ((( Math.round(Math.toDegrees(OrientationData[0])))+180.0)*0.71);
        xz2 = (int) ((( Math.round(Math.toDegrees(OrientationData[1])))+180.0)*0.71);
        zy2 = (int) ((( Math.round(Math.toDegrees(OrientationData[2])))+180.0)*0.71);

        ((TextView) findViewById(R.id.xyValue2)).setText(String.valueOf(xy2));
        ((TextView) findViewById(R.id.xzValue2)).setText(String.valueOf(xz2));
        ((TextView) findViewById(R.id.zyValue2)).setText(String.valueOf(zy2));


        int color = Color.rgb(xy2, xz2, zy2);
        findViewById(layout2).setBackgroundColor(color);
        int r =(rgb>>16) & 0xFF;
        int g =(rgb>>8) & 0xFF;
        int b =(rgb>>0) & 0xFF;
        if((r-20 < xy2 && r+20 < xy2 )&&(g-20 < xz2 && g+20 < xz2)&&(b-20 < zy2 && b+20 < zy2)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Win", Toast.LENGTH_SHORT);
            toast.show();
            rgb = rnd();
            findViewById(layout1).setBackgroundColor(rgb);
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