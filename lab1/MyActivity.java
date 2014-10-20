package com.example.myapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.Random;


public class MyActivity extends Activity implements View.OnClickListener{

    private FrameLayout frameLayout1;
    private FrameLayout frameLayout2;
    private FrameLayout frameLayout3;
    private FrameLayout frameLayout4;
    private FrameLayout frameLayout5;
    private FrameLayout frameLayout6;
    private Button Button;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            frameLayout1 = (FrameLayout) findViewById(R.id.frameLayout1);
            frameLayout2 = (FrameLayout) findViewById(R.id.frameLayout2);
            frameLayout3 = (FrameLayout) findViewById(R.id.frameLayout3);
            frameLayout4 = (FrameLayout) findViewById(R.id.frameLayout4);
            frameLayout5 = (FrameLayout) findViewById(R.id.frameLayout5);
            frameLayout6 = (FrameLayout) findViewById(R.id.frameLayout6);
            Button = (Button)findViewById(R.id.button);
            Button.setOnClickListener(this);
        }

    public void onClick(View v) {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        frameLayout1.setBackgroundColor(color);
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        frameLayout2.setBackgroundColor(color);
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        frameLayout3.setBackgroundColor(color);
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        frameLayout4.setBackgroundColor(color);
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        frameLayout5.setBackgroundColor(color);
        color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        frameLayout6.setBackgroundColor(color);

    }
}
