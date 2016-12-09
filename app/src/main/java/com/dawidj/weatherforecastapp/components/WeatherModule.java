package com.dawidj.weatherforecastapp.components;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dawidj on 01.12.2016.
 */
@Module
public class WeatherModule {

    @Provides
    @Singleton
    @Named("darksky")
    Retrofit darkskyRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.darksky.net/forecast/555119929f4837cddd4ce3f097bf63f1/")
                .build();
    }

    @Provides
    @Singleton
    @Named("autocomplete")
    Retrofit autocompleteRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://maps.googleapis.com/maps/api/place/autocomplete/")
                .build();
    }

    @Provides
    @Singleton
    @Named("details")
    Retrofit detailRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://maps.googleapis.com/maps/api/place/details/")
                .build();
    }


    @Provides
    @Singleton
    EventBus eventBus() {
        return new EventBus().getDefault();

    }

}