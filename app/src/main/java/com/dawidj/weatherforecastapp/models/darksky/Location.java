package com.dawidj.weatherforecastapp.models.darksky;

import android.databinding.BaseObservable;

import com.dawidj.weatherforecastapp.BR;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class Location extends BaseObservable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR._all);
    }
}
