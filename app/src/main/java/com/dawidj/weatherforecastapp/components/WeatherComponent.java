package com.dawidj.weatherforecastapp.components;

import com.dawidj.weatherforecastapp.viewModel.CityViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dawidj on 01.12.2016.
 */
@Singleton
@Component (modules=WeatherModule.class)
public interface WeatherComponent {
    void inject(CityViewModel cityViewModel);
}
