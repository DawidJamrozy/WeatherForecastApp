package com.dawidj.weatherforecastapp.viewModel;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
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
import static com.dawidj.weatherforecastapp.utils.Const.GOOGLE_GEOCODING_KEY;
import static com.dawidj.weatherforecastapp.utils.Const.GOOGLE_PLACES_KEY;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_CITIES;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_EXCLUDE;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_ID;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_LNG;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_LOCATION_TYPE;
import static com.dawidj.weatherforecastapp.utils.Const.KEY_RESULT_TYPE;
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
    private final PublishSubject<String> cityDetailsDataObservable = PublishSubject.create();
    private boolean progressBarVisibility;
    private int position;
    private SearchViewDataListener searchViewDataListener;
    private Context context;
    private CityLatLng testLatLng;

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

    @Inject
    @Named("geocode")
    Retrofit retrofitGeocode;

    public SearchViewModel(SearchViewDataListener searchViewDataListener, Context context) {
        this.searchViewDataListener = searchViewDataListener;
        this.context = context;
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
    public void insertCityToDatabase2(City value) {
        setCityData2(value);
        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(value));
        searchViewDataListener.newCityAdded(value.getId());
    }

    public void startRxStream() {

        final WeatherApi serviceAutocomplete = retrofitAutocomplete.create(WeatherApi.class);

        final WeatherApi serviceDetails = retrofitDetails.create(WeatherApi.class);

        final WeatherApi serviceWeather = retrofitDarksky.create(WeatherApi.class);

        final WeatherApi serviceGeocode = retrofitGeocode.create(WeatherApi.class);

        cityDetailsDataObservable
                .debounce(100, TimeUnit.MILLISECONDS)
                .flatMap(s -> serviceGeocode.getCityFromLatLng(s, KEY_LOCATION_TYPE, KEY_RESULT_TYPE, KEY_LNG, GOOGLE_GEOCODING_KEY)
                        .doOnError(e -> e.printStackTrace()))
                .flatMap(geocodeCity -> serviceDetails.getCityLatLng(geocodeCity.getResults().get(0).getPlaceId(), KEY_LNG, GOOGLE_PLACES_KEY)
                .doOnError(e -> e.printStackTrace()))
                .flatMap(cityLatLng -> {
                    testLatLng = cityLatLng;
                    return serviceWeather.getCity(getLat(cityLatLng), getLng(cityLatLng), KEY_LNG, KEY_EXCLUDE, KEY_UNITS).doOnError(e -> Timber.d("doOnError"));
                })
//                .flatMap(cityLatLng -> serviceWeather.getCity(getLat(cityLatLng), getLng(cityLatLng), KEY_LNG, KEY_EXCLUDE, KEY_UNITS)
//                        .doOnError(e -> Timber.d("doOnError")))
//                .flatMap(geocodeCity -> serviceWeather.getCity(geocodeCity.getResults().get(0).getGeometry().getLocation().getLat().toString(),
//                        geocodeCity.getResults().get(0).getGeometry().getLocation().getLng().toString(), KEY_LNG, KEY_EXCLUDE, KEY_UNITS)
//                        .doOnError(e -> e.printStackTrace()))
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
                        Timber.d("onNext(): ");
                        if (value != null) insertCityToDatabase2(value);
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
//https://maps.googleapis.com/maps/api/geocode/json?latlng=52.2297,21.0122&location_type=APPROXIMATE&result_type=locality&language=en&key=AIzaSyCP0kgQUfyHbrENCdUUi436K-zpJ8xiKII
//        cityDetailsDataObservable
//                .debounce(100, TimeUnit.MILLISECONDS)
//                .flatMap(cityDetails -> serviceWeather.getCity(cityDetails.getLat(), cityDetails.getLng(), KEY_LNG, KEY_EXCLUDE, KEY_UNITS)
//                        .doOnError(e -> Timber.d("doOnError")))
//                .retry()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<City>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        compositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(City value) {
//                        Timber.d("onNext(): ");
//                        if (value != null) insertCityToDatabase(value);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        e.printStackTrace();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Timber.i("onComplete(): ");
//                    }
//                });

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
                                .onErrorResumeNext(throwable -> {
                                    return Observable.empty();
                                })
                                .flatMapIterable(cityID -> cityID.getPredictions())
                                .flatMap(prediction -> serviceDetails.getCityLatLng(prediction.getPlaceId(), KEY_LNG, GOOGLE_PLACES_KEY))
                                .onErrorResumeNext(throwable -> {
                                    return Observable.empty();
                                })
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

    public void setCityData2(City city) {
        String name = testLatLng.getResult().getName();

        city.setName(name);
        city.setAdressDescription(testLatLng.getResult().getFormattedAddress());
        city.setRefreshDate(new SimpleDateFormat(DATE_FORMAT).format(System.currentTimeMillis()));
        city.setPlaceId(testLatLng.getResult().getPlaceId());
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

        if (text.equals("")) {
            setProgressBarVisibility(false);
        } else {
            setProgressBarVisibility(true);
        }
        textWatcherObservable.onNext(charSequence.toString());
    }

    public void locateMe(View view) {
        Timber.d("locateMe(): 1");
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();
                Timber.d("locateMe(): " + lat);
                Timber.d("locateMe(): " + lng);
//                CityDetails cityDetails = new CityDetails();
//                cityDetails.setLat(Double.toString(lat));
//                cityDetails.setLng(Double.toString(lng));
                //AIzaSyCP0kgQUfyHbrENCdUUi436K-zpJ8xiKII
                cityDetailsDataObservable.onNext(Double.toString(lat) + "," + Double.toString(lng));
                Timber.d("locateMe(): end");
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
//        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//        double lat = location.getLatitude();
//        double lng = location.getLongitude();
//        Timber.d("locateMe(): " + lat);
//        Timber.d("locateMe(): " + lng);
//        CityLatLng cityLatLng = new CityLatLng();
//        cityLatLng.getResult().getGeometry().getLocation().setLat(lat);
//        cityLatLng.getResult().getGeometry().getLocation().setLng(lng);
//
//        cityWeatherDataObservable.onNext(cityLatLng);
//        Timber.d("locateMe(): end");
    }
}
