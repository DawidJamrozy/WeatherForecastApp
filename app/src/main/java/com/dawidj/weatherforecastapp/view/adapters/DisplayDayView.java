package com.dawidj.weatherforecastapp.view.adapters;

import com.dawidj.weatherforecastapp.models.DayData;

import java.util.List;

/**
 * Created by Dawidj on 03.12.2016.
 */

public interface DisplayDayView {
    void displayDayList(List<DayData> dayDataList);
}
