package com.dawidj.weatherforecastapp.utils.busevent;

import com.dawidj.weatherforecastapp.models.details.CityLatLng;

import java.util.List;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class AddLocation {

    List<CityLatLng> cityLatLngs;

    public AddLocation(List<CityLatLng> cityLatLngs) {
        this.cityLatLngs = cityLatLngs;
    }

    public List<CityLatLng> getCityLatLngs() {
        return cityLatLngs;
    }
}
