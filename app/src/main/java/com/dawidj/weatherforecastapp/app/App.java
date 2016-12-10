package com.dawidj.weatherforecastapp.app;

import android.app.Application;

import com.dawidj.weatherforecastapp.components.DaggerWeatherComponent;
import com.dawidj.weatherforecastapp.components.WeatherComponent;
import com.dawidj.weatherforecastapp.components.WeatherModule;

import timber.log.Timber;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class App extends Application {

    private static App instance;
    private WeatherComponent weatherComponent;

    public WeatherComponent getWeatherComponent() {
        return weatherComponent;
    }

    public static App getApplication() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        instance = this;

        weatherComponent = DaggerWeatherComponent.builder()
                .weatherModule(new WeatherModule())
                .build();

       /* DevOpenHelper helper = new DevOpenHelper(this, "city-db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();*/
    }
}
