package com.dawidj.weatherforecastapp.utils;

import io.realm.RealmChangeListener;

/**
 * Created by djamrozy on 16.12.2016.
 */

public interface RealmDataBinding {
    interface Factory {
        RealmChangeListener create();
    }

    RealmDataBinding.Factory FACTORY = () -> element -> {
        if(element instanceof RealmDataBinding) {
            ((RealmDataBinding)element).notifyChange();
        }
    };

    void notifyChange();
}