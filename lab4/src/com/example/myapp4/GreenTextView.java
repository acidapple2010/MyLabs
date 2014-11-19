package com.example.myapp4;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by mac on 17.11.14.
 */
public class GreenTextView  extends TextView{
    public GreenTextView(Context context) {
        super(context);
        setTextColor(Color.parseColor("#00FF00"));
    }

    public GreenTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextColor(Color.parseColor("#00FF00"));
    }

    public GreenTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setTextColor(Color.parseColor("#00FF00"));
    }


}
