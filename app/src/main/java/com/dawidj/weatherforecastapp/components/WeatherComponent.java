package com.dawidj.weatherforecastapp.components;

import com.dawidj.weatherforecastapp.view.CityViewFragment;
import com.dawidj.weatherforecastapp.view.LocationActivity;
import com.dawidj.weatherforecastapp.viewModel.CityViewModel;
import com.dawidj.weatherforecastapp.viewModel.LocationViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dawidj on 01.12.2016.
 */
@Singleton
@Component (modules=WeatherModule.class)
public interface WeatherComponent {
    void inject(CityViewModel cityViewModel);
    void inject(CityViewFragment cityViewFragment);
    void inject(LocationViewModel locationViewModel);
    void inject (LocationActivity locationActivity);
}
