package com.dawidj.weatherforecastapp.utils;

import com.dawidj.weatherforecastapp.viewModel.CityViewModel;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * Created by Dawidj on 28.10.2016.
 */

public class AxisValueFormatter implements IAxisValueFormatter {

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return CityViewModel.hours[(int) value];
    }

}
