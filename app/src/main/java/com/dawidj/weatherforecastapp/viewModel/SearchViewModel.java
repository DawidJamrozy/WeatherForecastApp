package com.dawidj.weatherforecastapp.viewModel;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.api.WeatherApi;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.models.darksky.Currently;
import com.dawidj.weatherforecastapp.models.darksky.Daily;
import com.dawidj.weatherforecastapp.models.darksky.DailyData;
import com.dawidj.weatherforecastapp.models.darksky.Hourly;
import com.dawidj.weatherforecastapp.models.darksky.HourlyData;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;
import com.dawidj.weatherforecastapp.utils.listeners.SearchViewDataListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Retrofit;
import timber.log.Timber;

import static com.dawidj.weatherforecastapp.utils.Const.DATE_FORMAT;
import static com.dawidj.weatherforecastapp.utils.Const.GOOGLE_PLACES_KEY;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_CITIES;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_EXCLUDE;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_ID;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_LNG;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_UNITS;


/**
 * Created by Dawidj on 04.12.2016.
 */

public class SearchViewModel extends BaseObservable {

    private List<CityLatLng> cityLatLngList = new ArrayList<>();
    private ObservableField<String> cityName = new ObservableField<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final PublishSubject<String> textWatcherObservable = PublishSubject.create();
    private final PublishSubject<CityLatLng> cityWeatherDataObservable = PublishSubject.create();
    private boolean progressBarVisibility;
    private int position;
    private SearchViewDataListener searchViewDataListener;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ObservableField<String> getCityName() {
        return cityName;
    }

    public List<CityLatLng> getCityLatLngList() {
        return cityLatLngList;
    }

    public boolean isProgressBarVisibility() {
        return progressBarVisibility;
    }

    public void setProgressBarVisibility(boolean progressBarVisibility) {
        this.progressBarVisibility = progressBarVisibility;
        notifyPropertyChanged(BR._all);
    }

    @Inject
    Realm realm;

    @Inject
    @Named("darksky")
    Retrofit retrofitDarksky;

    @Inject
    @Named("autocomplete")
    Retrofit retrofitAutocomplete;

    @Inject
    @Named("details")
    Retrofit retrofitDetails;

    public SearchViewModel(SearchViewDataListener searchViewDataListener) {
        this.searchViewDataListener = searchViewDataListener;
    }

    public void addCity(final int position) {
        if (cityLatLngList.get(position).isExistInDb())
            return;
        setPosition(position);
        cityWeatherDataObservable.onNext(cityLatLngList.get(position));
    }

    public void insertCityToDatabase(City value) {
        setCityData(value);
        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(value));
        searchViewDataListener.newCityAdded(value.getId());
    }

    public void startRxStream() {

        final WeatherApi serviceAutocomplete = retrofitAutocomplete.create(WeatherApi.class);

        final WeatherApi serviceDetails = retrofitDetails.create(WeatherApi.class);

        final WeatherApi serviceWeather = retrofitDarksky.create(WeatherApi.class);

        cityWeatherDataObservable
                .debounce(100, TimeUnit.MILLISECONDS)
                .flatMap(cityLatLng -> serviceWeather.getCity(getLat(cityLatLng), getLng(cityLatLng), KEY_LNG, KEY_EXCLUDE, KEY_UNITS)
                        .doOnError(e -> Timber.d("doOnError")))
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<City>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(City value) {
                        if (value != null) insertCityToDatabase(value);
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

        textWatcherObservable
                .debounce(500, TimeUnit.MILLISECONDS)
                .filter(s -> !s.isEmpty())
                .flatMapSingle(new Function<String, SingleSource<List<CityLatLng>>>() {
                    @Override
                    public SingleSource<List<CityLatLng>> apply(String s) throws Exception {
                        return serviceAutocomplete.getCityName(s, KEY_CITIES, KEY_LNG, GOOGLE_PLACES_KEY)
                                .onErrorResumeNext(throwable -> {return Observable.empty();})
                                .flatMapIterable(cityID -> cityID.getPredictions())
                                .flatMap(prediction -> serviceDetails.getCityLatLng(prediction.getPlaceId(), KEY_LNG, GOOGLE_PLACES_KEY))
                                .onErrorResumeNext(throwable -> {return Observable.empty();})
                                .toList();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<CityLatLng>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(List<CityLatLng> value) {
                        cityLatLngList = value;
                        searchViewDataListener.notifyAdapter(value);
                        setProgressBarVisibility(false);
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

    public void onDestroy() {
        compositeDisposable.clear();
    }

    public int getPrimaryKeyId(Class data) {
        int key;
        try {
            key = realm.where(data).max(KEY_ID).intValue() + 1;
        } catch (ArrayIndexOutOfBoundsException ex) {
            key = 0;
        } catch (NullPointerException ex) {
            key = 0;
        }
        return key;
    }

    public String getLat(CityLatLng cityLatLng) {
        return cityLatLng.getResult().getGeometry().getLocation().getLat().toString();
    }

    public String getLng(CityLatLng cityLatLng) {
        return cityLatLng.getResult().getGeometry().getLocation().getLng().toString();
    }

    public Integer getLastSortedPosition() {
        RealmResults<City> cityList = realm.where(City.class).findAll();

        if (cityList.isEmpty()) {
            return 0;
        } else {
            City city = Collections.max(cityList, (city1, city2) -> city1.getSortPosition() - city2.getSortPosition());
            return city.getSortPosition() + 1;
        }
    }

    public void setCityData(City city) {
        String name = cityLatLngList.get(getPosition()).getResult().getName();

        city.setName(name);
        city.setAdressDescription(cityLatLngList.get(getPosition()).getResult().getFormattedAddress());
        city.setRefreshDate(new SimpleDateFormat(DATE_FORMAT).format(System.currentTimeMillis()));
        city.setPlaceId(cityLatLngList.get(getPosition()).getResult().getPlaceId());
        city.setId(getPrimaryKeyId(City.class));
        city.setSortPosition(getLastSortedPosition());
        city.getCurrently().setName(name);
        city.getCurrently().setId(getPrimaryKeyId(Currently.class));
        city.getDaily().setName(name);
        city.getDaily().setId(getPrimaryKeyId(Daily.class));
        city.getHourly().setName(name);
        city.getHourly().setId(getPrimaryKeyId(Hourly.class));

        int idDailyData = getPrimaryKeyId(DailyData.class);
        int idHourlyData = getPrimaryKeyId(HourlyData.class);

        for (DailyData data : city.getDaily().getData()) {
            data.setId(idDailyData);
            data.setName(name);
            data.setPlaceId(city.getPlaceId());
            idDailyData++;
        }

        for (HourlyData data : city.getHourly().getData()) {
            data.setId(idHourlyData);
            data.setName(name);
            data.setPlaceId(city.getPlaceId());
            idHourlyData++;
        }
    }

    public void hideKeyboard(View view) {
        searchViewDataListener.loseFocus();
        Timber.d("hideKeyboard(): ");
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString();
        cityLatLngList.clear();
        searchViewDataListener.notifyAdapter(cityLatLngList);

        if(text.equals("")) {
            setProgressBarVisibility(false);
        } else {
            setProgressBarVisibility(true);
        }
        textWatcherObservable.onNext(charSequence.toString());
    }
}
