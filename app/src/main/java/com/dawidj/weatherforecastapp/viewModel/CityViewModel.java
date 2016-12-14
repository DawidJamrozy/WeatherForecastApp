package com.dawidj.weatherforecastapp.viewModel;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.models.dbtest.DailyData;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.ValueFormatter;
import com.dawidj.weatherforecastapp.utils.busevent.LineChartEvent;
import com.dawidj.weatherforecastapp.view.adapters.DisplayDayView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityViewModel {
    
    private City city;
    public final static String[] hours = new String[25];
    private DisplayDayView displayDayView;
    private String cityName;
    private List<com.dawidj.weatherforecastapp.models.weather.DayData> day = new ArrayList<>();

    public void setDisplayDayView(DisplayDayView displayDayView) {
        this.displayDayView = displayDayView;
    }

    public City getCity() {
        return city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Inject
    EventBus eventBus;

    public CityViewModel(City city) {
        this.city = city;
    }

    public void getWeatherData(City city) {
                    if(!day.isEmpty()) {
                        day.clear();
                    }
                    for (int i = 0; i < 7; i++) {
                        com.dawidj.weatherforecastapp.models.weather.DayData dayData = new com.dawidj.weatherforecastapp.models.weather.DayData();
                        dayData.setIcon(asd(city, i).getIcon());
                        dayData.setTempMin(asd(city, i).getTemperatureMin().intValue());
                        dayData.setTempMax(asd(city, i).getTemperatureMax().intValue());
                        dayData.setTime(asd(city, i).getTime());
                        day.add(dayData);
                    }

                    if (displayDayView != null) {
                        displayDayView.displayDayList(day);
                    }
    }

    public void setDayChart(City city) {

        List<Entry> entries = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(Const.HOUR_MINUTE);
        sdf.setTimeZone(TimeZone.getTimeZone(city.getTimezone()));

        //temp - add data to calculate min temperature for chart axis
        //entries - add data to display temp for every hour
        //hours - add data to display time in chart
        for (int i = 0; i < 25; i++) {
            temp.add(city.getHourly().getData().get(i).getTemperature().intValue());
            entries.add(new Entry(i, city.getHourly().getData().get(i).getTemperature().intValue()));
            hours[i] = sdf.format(new Date(city.getHourly().getData().get(i).getTime() * 1000L));
        }

        int minTemp = Collections.min(temp) - 2;
        int maxTemp = Collections.max(temp) + 2;

        LineDataSet lineDataSet = new LineDataSet(entries, "Label");
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setCircleHoleRadius(2.5f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueFormatter(new ValueFormatter());
        lineDataSet.setColor(R.color.colorAccent);
        lineDataSet.setValueTextColor(R.color.colorPrimary);
        Timber.i("setDayChart(): ");
        eventBus.post(new LineChartEvent(lineDataSet, minTemp, maxTemp));
    }

    public DailyData asd(City city, int i) {
        return city.getDaily().getData().get(i);
    }
}
