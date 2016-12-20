package com.dawidj.weatherforecastapp.utils.listeners;

import com.dawidj.weatherforecastapp.models.details.CityLatLng;

import java.util.List;

/**
 * Created by Dawidj on 20.12.2016.
 */

public interface SearchViewDataListener {
    void newCityAdded(int position);
    void notifyAdapter(List<CityLatLng> list);
}
