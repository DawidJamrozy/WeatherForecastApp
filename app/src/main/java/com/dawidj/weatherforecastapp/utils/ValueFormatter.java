package com.dawidj.weatherforecastapp.utils;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Created by Dawidj on 28.10.2016.
 */

public class ValueFormatter implements IValueFormatter {

    private final String DEGREE = "\u00b0";
    private DecimalFormat decimalFormat;

    public ValueFormatter() {

        decimalFormat = new DecimalFormat("###,###,##0");
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        return decimalFormat.format(value) + DEGREE;
    }
}
