package com.dawidj.weatherforecastapp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Dawidj on 03.01.2017.
 */

public class ThinTextView extends TextView {

    public ThinTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/heebo.ttf"));
    }
}
