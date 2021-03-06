package com.dawidj.weatherforecastapp.components;

import com.dawidj.weatherforecastapp.view.CityActivity;
import com.dawidj.weatherforecastapp.view.MainActivity;
import com.dawidj.weatherforecastapp.view.MyCitiesViewActivity;
import com.dawidj.weatherforecastapp.view.SearchActivity;
import com.dawidj.weatherforecastapp.view.adapters.CitiesRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.view.adapters.CitiesRecyclerViewHolder;
import com.dawidj.weatherforecastapp.view.adapters.SearchRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.CityViewModel;
import com.dawidj.weatherforecastapp.viewModel.MyCitiesViewModel;
import com.dawidj.weatherforecastapp.viewModel.SearchViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Dawidj on 01.12.2016.
 */
@Singleton
@Component(modules = WeatherModule.class)
public interface WeatherComponent {
    void inject(CityViewModel cityViewModel);

    void inject(MainActivity mainActivity);

    void inject(CitiesRecyclerViewAdapter citiesRecyclerViewAdapter);

    void inject(SearchRecyclerViewAdapter searchRecyclerViewAdapter);

    void inject(SearchViewModel searchViewModel);

    void inject(SearchActivity searchActivity);

    void inject(MyCitiesViewModel myCitiesViewModel);

    void inject(MyCitiesViewActivity myCitiesActivity);

    void inject(CitiesRecyclerViewHolder citiesRecyclerViewHolder);

    void inject(CityActivity cityActivity);
}
