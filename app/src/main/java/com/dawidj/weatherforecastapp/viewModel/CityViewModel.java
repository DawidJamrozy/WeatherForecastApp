package com.dawidj.weatherforecastapp.viewModel;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.widget.Toast;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.api.WeatherAPI;
import com.dawidj.weatherforecastapp.models.City;
import com.dawidj.weatherforecastapp.models.Daily;
import com.dawidj.weatherforecastapp.utils.AxisValueFormatter;
import com.dawidj.weatherforecastapp.utils.ValueFormatter;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.R.attr.name;
import static android.content.ContentValues.TAG;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityViewModel {

    private Context context;
    private Address address;
    private City city = new City();
    private ArrayList<Daily> daily = new ArrayList<>();
    public final static String[] hours = new String[25];

    @Inject
    Retrofit retrofit;

    public CityViewModel(Context context) {
    this.context = context;
    }

    public void getWeatherData() {

        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());
            String cityName = name.get();
            try {
                List<Address> addressList = geocoder.getFromLocationName(cityName, 1);
                address = addressList.get(0);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(context, R.string.empty_city_name, Toast.LENGTH_SHORT).show();
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

            String lat = Double.toString(address.getLatitude());
            String lng = Double.toString(address.getLongitude());

            WeatherAPI service = retrofit.create(WeatherAPI.class);

            Call<City> call = service.getCity(lat, lng);

            call.enqueue(new Callback<City>() {
                @Override
                public void onResponse(Call<City> call, Response<City> response) {
                    Log.i(TAG, "onResponse: Success");
                    City data = response.body();

                    city.setName(name.get());
                    city.setLatitude(data.getLatitude());
                    city.setLongitude(data.getLongitude());
                    city.setTimezone(data.getTimezone());
                    city.setOffset(data.getOffset());
                    city.setFlags(data.getFlags());
                    city.setDaily(data.getDaily());
                    city.setHourly(data.getHourly());
                    city.setCurrently(data.getCurrently());

                    if (daily.size() != 0) {
                        daily.clear();
                    }

                    for (int i = 0; i < 7; i++) {
                        daily.add(city.getDaily());
                    }

                    //TODO use eventbus to notify view about recyclerViewadapter data change
                    mRecycler.get().notifyDataSetChanged();
                    //setDetailsData();
                    setDayChart();
                }

                @Override
                public void onFailure(Call<City> call, Throwable t) {
                    Log.i(TAG, "onFailure: " + t.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDayChart() {

        List<Entry> entries = new ArrayList<Entry>();

        for (int i = 0; i < 25; i++) {
            Double a = city.getHourly().getData().get(i).getTemperature();
            entries.add(new Entry(i, a.intValue()));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Label");
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setCircleHoleRadius(2.5f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueFormatter(new ValueFormatter());
        lineDataSet.setColor(R.color.colorAccent);
        lineDataSet.setValueTextColor(R.color.colorPrimary);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone(city.getTimezone()));

        for (int i = 0; i < hours.length; i++) {
            int unixTime = city.getHourly().getData().get(i).getTime();
            Date date = new Date(unixTime * 1000L);
            String hour = sdf.format(date);
            hours[i] = hour;
        }

        YAxis leftAxis = cityFragment.getLineChart().getAxisLeft();
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = cityFragment.getLineChart().getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisMinimum(0f);

        XAxis downAxis = cityFragment.getLineChart().getXAxis();
        downAxis.setLabelCount(25);
        downAxis.setDrawGridLines(false);
        downAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        downAxis.setValueFormatter(new AxisValueFormatter());

        Description description = new Description();
        description.setText("");
        LineData lineData = new LineData(lineDataSet);
        cityFragment.getLineChart().setData(lineData);
        cityFragment.getLineChart().getLegend().setEnabled(false);
        cityFragment.getLineChart().setTouchEnabled(false);
        cityFragment.getLineChart().setDescription(description);
        cityFragment.getLineChart().canScrollHorizontally(1);
        cityFragment.getLineChart().invalidate();
    }
}
