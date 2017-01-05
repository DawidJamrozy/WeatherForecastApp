package com.dawidj.weatherforecastapp.app;

import android.app.Application;

import com.dawidj.weatherforecastapp.components.DaggerWeatherComponent;
import com.dawidj.weatherforecastapp.components.WeatherComponent;
import com.dawidj.weatherforecastapp.components.WeatherModule;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        Timber.plant(new Timber.DebugTree());
        Realm.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        instance = this;

        weatherComponent = DaggerWeatherComponent.builder()
                .weatherModule(new WeatherModule())
                .build();

        Realm.init(this);
    }
}
