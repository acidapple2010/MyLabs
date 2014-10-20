package com.example.myapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.Random;


public class MyActivity extends Activity implements View.OnClickListener {

    int[] layouts = {R.id.frameLayout1, R.id.frameLayout2, R.id.frameLayout3, R.id.frameLayout4, R.id.frameLayout5, R.id.frameLayout6};
    private Random rnd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.button).setOnClickListener(this);
        rnd = new Random();
    }

    public void onClick(View v) {
        for (int id : layouts) {
            findViewById(id).setBackgroundColor(rnd());
        }
        /*
        for (int i = 0; i < layouts.length; i++) {
            int id = layouts[i];
            findViewById(id).setBackgroundColor(rnd());
        }


        findViewById(R.id.frameLayout1).setBackgroundColor(rnd());
        findViewById(R.id.frameLayout2).setBackgroundColor(rnd());
        findViewById(R.id.frameLayout3).setBackgroundColor(rnd());
        findViewById(R.id.frameLayout4).setBackgroundColor(rnd());
        findViewById(R.id.frameLayout5).setBackgroundColor(rnd());
        findViewById(R.id.frameLayout6).setBackgroundColor(rnd());*/

    }

    public int rnd() {
        return Color.rgb(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

}
