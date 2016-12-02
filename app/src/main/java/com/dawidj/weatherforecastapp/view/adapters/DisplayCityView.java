package com.dawidj.weatherforecastapp.view.adapters;

import com.dawidj.weatherforecastapp.models.City;
import com.dawidj.weatherforecastapp.models.DailyData;

import java.util.List;

/**
 * Created by djamrozy on 02.12.2016.
 */

public interface DisplayCityView {

    void displayCities(City city);
    void displayDailyList(List<DailyData> dailyList);
}
