package com.dawidj.weatherforecastapp.viewModel;

import com.dawidj.weatherforecastapp.models.dbtest.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class MyCitiesViewModel {


    private List<City> cityList = new ArrayList<>();

    public List<City> getCityList() {
        return cityList;
    }

    public MyCitiesViewModel() {

    }
}
