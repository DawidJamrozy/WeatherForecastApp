package com.dawidj.weatherforecastapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by djamrozy on 21.12.2016.
 */

public class SingleToast {

    private static Toast toast;

    public static void show(Context context, String text, int duration) {
        if(toast != null)
            toast.cancel();
        toast = Toast.makeText(context, text , duration);
        toast.show();
    }
}
