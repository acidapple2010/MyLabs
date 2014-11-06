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

public class MyActivity extends Activity implements SensorEventListener{

    private SensorManager msensorManager;

    private float[] rotationMatrix;
    private float[] accelData;
    private float[] magnetData;
    private float[] OrientationData;

    int layout1 = R.id.layout1;
    int layout2 = R.id.layout2;
    private int rnd;
    private int col = 180;
    private int col2 = 90;
    //public int rnd() {
        //return Color.rgb(rnd.nextInt(col), rnd.nextInt(col2), rnd.nextInt(col));
    //}
    public TextView xyView1;
    public TextView xzView1;
    public TextView zyView1;
    public TextView xyView2;
    public TextView xzView2;
    public TextView zyView2;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        rotationMatrix = new float[16];
        accelData = new float[3];
        magnetData = new float[3];
        OrientationData = new float[3];

        setContentView(R.layout.main);
        //rnd = new Random();
        rnd = Color.rgb(-174, 2, 0);
        findViewById(layout1).setBackgroundColor(rnd);

        xyView1 = (TextView) findViewById(R.id.xyValue1);
        xzView1 = (TextView) findViewById(R.id.xzValue1);
        zyView1 = (TextView) findViewById(R.id.zyValue1);

    }

    public void onSensorChanged(SensorEvent event) {
        loadNewSensorData(event);
        SensorManager.getRotationMatrix(rotationMatrix, null, accelData, magnetData);
        SensorManager.getOrientation(rotationMatrix, OrientationData);

        xyView2 = (TextView) findViewById(R.id.xyValue2);
        xzView2 = (TextView) findViewById(R.id.xzValue2);
        zyView2 = (TextView) findViewById(R.id.zyValue2);

        xyView2.setText(String.valueOf(Math.round(Math.toDegrees(OrientationData[0]))));
        xzView2.setText(String.valueOf(Math.round(Math.toDegrees(OrientationData[1]))));
        zyView2.setText(String.valueOf(Math.round(Math.toDegrees(OrientationData[2]))));

        int xx = Integer.parseInt(xyView2.getText().toString());
        int xz = Integer.parseInt(xzView2.getText().toString());
        int xy = Integer.parseInt(zyView2.getText().toString());

        int color = Color.rgb(xx, xz, xy);
        findViewById(layout2).setBackgroundColor(color);
        if (color == rnd) {
            Toast toast = Toast.makeText(getApplicationContext(), "Game over", Toast.LENGTH_SHORT);
            toast.show();
            onPause();
            //finish();
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