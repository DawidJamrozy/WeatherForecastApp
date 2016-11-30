package com.dawidj.weatherforecastapp.app;

import android.app.Application;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

    }

    public Application getApplication() {
        return instance;
    }
}
