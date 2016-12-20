package com.dawidj.weatherforecastapp.viewModel;

import android.view.View;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.api.WeatherApi;
import com.dawidj.weatherforecastapp.models.CityDetails;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.models.dbtest.DailyData;
import com.dawidj.weatherforecastapp.models.dbtest.DayData;
import com.dawidj.weatherforecastapp.models.dbtest.HourlyData;
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

import io.reactivex.Observable;
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
import timber.log.Timber;

import static com.dawidj.weatherforecastapp.utils.Const.KEY_EXCLUDE;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_PL_LNG;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_UNITS;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityViewModel {

    private City city;
    public final static String[] hours = new String[25];
    private String cityName;
    private List<DayData> dayDatasList = new ArrayList<>();
    private PublishSubject<CityDetails> refreshObservable = PublishSubject.create();
    private CompositeDisposable compositDisposable = new CompositeDisposable();
    private CityViewDataListener cityViewDataListener;


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(minTemp);
        leftAxis.setAxisMaximum(maxTemp);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisMinimum(minTemp);
        rightAxis.setAxisMaximum(maxTemp);

        XAxis downAxis = lineChart.getXAxis();
        downAxis.setLabelCount(25);
        downAxis.setDrawGridLines(false);
        downAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        downAxis.setValueFormatter(new AxisValueFormatter());

        Description description = new Description();
        description.setText("");
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setDescription(description);
        lineChart.canScrollHorizontally(1);
        lineChart.invalidate();
        lineChart.notifyDataSetChanged();
    }

    public DailyData getDataToRecycelerView(int i) {
        return city.getDaily().getData().get(i);
    }

    public void refreshData() {
        CityDetails cityDetails = new CityDetails();
        cityDetails.setName(city.getName());
        cityDetails.setLat(city.getLatitude().toString());
        cityDetails.setLng(city.getLongitude().toString());
        refreshObservable.onNext(cityDetails);
    }

    public void refreshWeatherData() {
        WeatherApi serviceWeather = retrofitDarksky.create(WeatherApi.class);

        refreshObservable.debounce(100, TimeUnit.MILLISECONDS)
                .flatMap(new Function<CityDetails, ObservableSource<City>>() {
                    @Override
                    public ObservableSource<City> apply(CityDetails city) throws Exception {
                        return serviceWeather.getCity(city.getLat(),
                                city.getLng(), KEY_PL_LNG, KEY_EXCLUDE, KEY_UNITS);
                    }
                })
                .onErrorResumeNext(new Function<Throwable, ObservableSource<City>>() {
                    @Override
                    public ObservableSource<City> apply(Throwable throwable) throws Exception {
                        return Observable.empty();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<City>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositDisposable.add(d);
                    }

                    @Override
                    public void onNext(City value) {
                        replaceDataInDatabase(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Timber.i("onComplete(): ");
                    }
                });
    }

    public void replaceDataInDatabase(City value) {
        value.setName(city.getName());
        value.setId(city.getId());
        value.getCurrently().setId(city.getCurrently().getId());
        value.getDaily().setId(city.getDaily().getId());
        value.getHourly().setId(city.getHourly().getId());

        int idDailyData = city.getDaily().getData().get(0).getId();
        int idHourlyData = city.getHourly().getData().get(0).getId();

        for (DailyData data : value.getDaily().getData()) {
            data.setId(idDailyData);
            idDailyData++;
        }

        for (HourlyData data : value.getHourly().getData()) {
            data.setId(idHourlyData);
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

}
