package com.dawidj.weatherforecastapp.utils;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineDataSet;

/**
 * Created by djamrozy on 02.12.2016.
 */

public class LineChartEvent {

    public Description description;
    public LineDataSet lineDataSet;

    public LineChartEvent(Description description, LineDataSet lineDataSet) {
        this.description = description;
        this.lineDataSet = lineDataSet;
    }
}
