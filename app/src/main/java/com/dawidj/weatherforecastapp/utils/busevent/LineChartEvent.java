package com.dawidj.weatherforecastapp.utils.busevent;

import com.github.mikephil.charting.data.LineDataSet;

/**
 * Created by djamrozy on 02.12.2016.
 */

public class LineChartEvent {

    public LineDataSet lineDataSet;
    public int minTemp;
    public int maxTemp;

    public LineChartEvent(LineDataSet lineDataSet, int minTemp, int maxTemp) {
        this.lineDataSet = lineDataSet;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }
}
