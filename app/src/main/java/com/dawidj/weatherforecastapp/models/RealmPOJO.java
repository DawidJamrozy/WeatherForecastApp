package com.dawidj.weatherforecastapp.models;

import android.databinding.BaseObservable;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by djamrozy on 16.12.2016.
 */

@RealmClass
public class RealmPOJO extends BaseObservable implements RealmModel {

    private String name;

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
    }
}
