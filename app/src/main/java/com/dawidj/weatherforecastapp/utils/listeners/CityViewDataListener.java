package com.dawidj.weatherforecastapp.utils.listeners;

/**
 * Created by Dawidj on 19.12.2016.
 */

public interface CityViewDataListener {
        void notifyDataChanged();
        void startMyCitiesActivity();
        void turnOffSwipeToRefresh();
}
