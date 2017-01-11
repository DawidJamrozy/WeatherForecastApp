package com.dawidj.weatherforecastapp.viewModel;

import android.databinding.BaseObservable;
import android.view.View;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.api.WeatherApi;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.models.CityDetails;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.models.darksky.DailyData;
import com.dawidj.weatherforecastapp.models.darksky.DayData;
import com.dawidj.weatherforecastapp.models.darksky.HourlyData;
import com.dawidj.weatherforecastapp.utils.AxisValueFormatter;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.ValueFormatter;
import com.dawidj.weatherforecastapp.utils.listeners.CityViewDataListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import retrofit2.Retrofit;


/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityViewModel extends BaseObservable {

    private City city;
    private List<DayData> dayDatasList = new ArrayList<>();
    private PublishSubject<CityDetails> refreshObservable = PublishSubject.create();
    private CompositeDisposable compositDisposable = new CompositeDisposable();
    private CityViewDataListener cityViewDataListener;
    public final static String[] hours = new String[25];

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
        notifyPropertyChanged(BR._all);
    }

    public List<DayData> getDayDatasList() {
        return dayDatasList;
    }

    @Inject
    Realm realm;

    @Inject
    @Named("darksky")
    Retrofit retrofitDarksky;

    public CityViewModel(City city, CityViewDataListener cityViewDataListener) {
        this.city = city;
        this.cityViewDataListener = cityViewDataListener;
    }

    public void getWeatherData() {
        if (!dayDatasList.isEmpty()) {
            dayDatasList.clear();
        }
        for (int i = 0; i < 7; i++) {
            DayData dayData = new DayData();
            dayData.setIcon(getDataToRecycelerView(i).getIcon());
            dayData.setTempMin(getDataToRecycelerView(i).getTemperatureMin().intValue());
            dayData.setTempMax(getDataToRecycelerView(i).getTemperatureMax().intValue());
            dayData.setTime(getDataToRecycelerView(i).getTime());
            dayDatasList.add(dayData);
        }
    }

    public void setDayChart(LineChart lineChart) {

        List<Entry> entries = new ArrayList<>();
        ArrayList<Integer> temperatureList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(Const.HOUR_MINUTE);
        sdf.setTimeZone(TimeZone.getTimeZone(city.getTimezone()));

        //temp - add data to calculate min temperature for chart axis
        //entries - add data to display temp for every hour
        //hours - add data to display time in chart
        for (int i = 0; i < 25; i++) {
            temperatureList.add(city.getHourly().getData().get(i).getTemperature().intValue());
            entries.add(new Entry(i, city.getHourly().getData().get(i).getTemperature().intValue()));
            hours[i] = sdf.format(new Date(city.getHourly().getData().get(i).getTime() * 1000L));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Label");
        customizeLineDataSet(lineDataSet);

        YAxis leftAxis = lineChart.getAxisLeft();
        setYAxis(leftAxis, temperatureList);

        YAxis rightAxis = lineChart.getAxisRight();
        setYAxis(rightAxis, temperatureList);

        XAxis downAxis = lineChart.getXAxis();
        setXAxis(downAxis);

        LineData lineData = new LineData(lineDataSet);

        customizeLineChart(lineChart, lineData);
    }

    private void setXAxis(XAxis xAxis) {
        xAxis.setLabelCount(25);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new AxisValueFormatter());
    }

    private void customizeLineDataSet(LineDataSet lineDataSet) {
        lineDataSet.setValueTextSize(12f);
        lineDataSet.setCircleHoleRadius(2.5f);
        lineDataSet.setCircleRadius(4f);
        lineDataSet.setValueFormatter(new ValueFormatter());
        lineDataSet.setColor(R.color.colorAccent);
        lineDataSet.setValueTextColor(R.color.colorPrimary);
    }

    private void customizeLineChart(LineChart lineChart, LineData lineData) {
        Description description = new Description();
        description.setText("");
        lineChart.setData(lineData);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setDescription(description);
        lineChart.canScrollHorizontally(1);
        lineChart.invalidate();
        lineChart.notifyDataSetChanged();
    }

    private void setYAxis(YAxis axis, ArrayList<Integer> temp) {
        axis.setDrawGridLines(false);
        axis.setDrawLabels(false);
        axis.setAxisMinimum(Collections.min(temp) - 2);
        axis.setAxisMaximum(Collections.max(temp) + 2);
    }

    private DailyData getDataToRecycelerView(int i) {
        return city.getDaily().getData().get(i);
    }

    public void refreshData() {
        CityDetails cityDetails = new CityDetails();
        cityDetails.setName(city.getName());
        cityDetails.setLat(city.getLatitude().toString());
        cityDetails.setLng(city.getLongitude().toString());
        refreshObservable.onNext(cityDetails);
    }

    public void startRxStream() {
        WeatherApi serviceWeather = retrofitDarksky.create(WeatherApi.class);

        refreshObservable.debounce(100, TimeUnit.MILLISECONDS)
                .flatMap(new Function<CityDetails, ObservableSource<City>>() {
                    @Override
                    public ObservableSource<City> apply(CityDetails city) throws Exception {
                        return serviceWeather.getCity(city.getLat(), city.getLng(), Const.KEY_LNG, Const.KEY_EXCLUDE, Const.KEY_UNITS)
                                .doOnError(e -> cityViewDataListener.turnOffSwipeToRefresh());
                    }
                })
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<City>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositDisposable.add(d);
                    }

                    @Override
                    public void onNext(City value) {
                        if (value != null) replaceDataInDatabase(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    private void replaceDataInDatabase(City value) {
        value.setName(city.getName());
        value.setAdressDescription(city.getAdressDescription());
        value.setPlaceId(city.getPlaceId());
        value.setId(city.getId());
        value.setRefreshDate(new SimpleDateFormat(Const.DATE_FORMAT).format(System.currentTimeMillis()));
        value.setSortPosition(city.getSortPosition());
        value.getCurrently().setId(city.getCurrently().getId());
        value.getDaily().setId(city.getDaily().getId());
        value.getHourly().setId(city.getHourly().getId());

        int idDailyData = city.getDaily().getData().get(0).getId();
        int idHourlyData = city.getHourly().getData().get(0).getId();

        for (DailyData data : value.getDaily().getData()) {
            data.setId(idDailyData);
            data.setPlaceId(city.getPlaceId());
            idDailyData++;
        }

        for (HourlyData data : value.getHourly().getData()) {
            data.setId(idHourlyData);
            data.setPlaceId(city.getPlaceId());
            idHourlyData++;
        }

        setCity(value);

        cityViewDataListener.notifyDataChanged();

        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(value));
    }

    public void onDestroy() {
        compositDisposable.clear();
    }

    public void startMyCitiesActivity(View view) {
        cityViewDataListener.startMyCitiesActivity();
    }

    public void startDarkSky(View view) {
        cityViewDataListener.openDarkSkyWebSite();
    }

    public boolean checkIfLastRefreshTimeIsMoreThanHalfHour() {
        if (getCurrentTime() - city.getCurrently().getTime() < Const.HALF_HOUR) {
            String info;
            long remaningTime = (((city.getCurrently().getTime() + Const.HALF_HOUR) - getCurrentTime()) / 60);
            if (remaningTime > 1) {
                info = App.getApplication().getString(R.string.more_than_min, remaningTime);
            } else {
                info = App.getApplication().getString(R.string.less_than_min);
            }
            cityViewDataListener.refreshInterval(info);
            return false;
        } else {
            return true;
        }
    }

    private long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }
}
