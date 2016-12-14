package com.dawidj.weatherforecastapp.viewModel;

import android.content.Context;
import android.location.Address;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.models.weather.City;
import com.dawidj.weatherforecastapp.models.weather.DailyData;
import com.dawidj.weatherforecastapp.models.weather.DayData;
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

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityViewModel {

    private Context context;
    private Address address;
    private City city = new City();
    public final static String[] hours = new String[25];
    private DisplayDayView displayDayView;
    private String cityName;
    private List<DayData> day = new ArrayList<>();

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



    public CityViewModel(Context context) {
        this.context = context;
    }

    public void getWeatherData() {
/*
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            List<Address> addressList = geocoder.getFromLocationName(getCityName(), 1);
            address = addressList.get(0);



            String lat = Double.toString(address.getLatitude());
            String lng = Double.toString(address.getLongitude());

            WeatherApi service = retrofit.create(WeatherApi.class);

            Call<City> call = service.getCity(lat, lng);

            call.enqueue(new Callback<City>() {
                @Override
                public void onResponse(Call<City> call, Response<City> response) {
                    Log.i(TAG, "onResponse: Success");

                    City data = response.body();

                    city.setName(getCityName());
                    city.setLatitude(data.getLatitude());
                    city.setLongitude(data.getLongitude());
                    city.setTimezone(data.getTimezone());
                    city.setDaily(data.getDaily());
                    city.setHourly(data.getHourly());
                    city.setCurrently(data.getCurrently());

                    if(!day.isEmpty()) {
                        day.clear();
                    }
                    for (int i = 0; i < 7; i++) {
                        DayData dayData = new DayData();
                        dayData.setIcon(asd(i).getIcon());
                        dayData.setTempMin(asd(i).getTemperatureMin().intValue());
                        dayData.setTempMax(asd(i).getTemperatureMax().intValue());
                        dayData.setTime(asd(i).getTime());
                        day.add(dayData);
                    }

                    if (displayDayView != null) {
                        displayDayView.displayDayList(day);
                    }


                    //TODO use eventbus to notify view about recyclerViewadapter data change
                    eventBus.post(new NewCity());

                    setDayChart();
                }

                @Override
                public void onFailure(Call<City> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void setDayChart() {

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

        eventBus.post(new LineChartEvent(lineDataSet, minTemp, maxTemp));
    }

    public DailyData asd(int i) {
        return city.getDaily().getData().get(i);
    }
}
