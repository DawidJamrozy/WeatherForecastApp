package com.dawidj.weatherforecastapp.view.adapters;

import com.dawidj.weatherforecastapp.models.darksky.City;

/**
 * Created by Dawidj on 18.12.2016.
 */

public interface MyCitiesDataListener {
    void deleteCityFromList(City city);
    void checkIfListIsEmpty();
}
