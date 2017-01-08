package com.dawidj.weatherforecastapp.viewModel;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.api.WeatherApi;
import com.dawidj.weatherforecastapp.models.autocomplete.CityID;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.models.darksky.Currently;
import com.dawidj.weatherforecastapp.models.darksky.Daily;
import com.dawidj.weatherforecastapp.models.darksky.DailyData;
import com.dawidj.weatherforecastapp.models.darksky.Hourly;
import com.dawidj.weatherforecastapp.models.darksky.HourlyData;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.listeners.SearchViewDataListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
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
    private CityLatLng locateMeCityLatLng;
    private boolean internetConnectionError;
    private boolean streamInterrupted;
    private long lastClickTime;
    private boolean locatingInProgress;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            String lat = Double.toString(location.getLatitude());
            String lng = Double.toString(location.getLongitude());
            cityDetailsDataObservable.onNext(lat + "," + lng);
            stopListeningForLocationChange();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
            searchViewDataListener.showToast(context.getString(R.string.localization_disabled));
            stopListeningForLocationChange();
            setProgressBarVisibility(false);
        }
    };
    private LocationManager locationManager;

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

    private void setProgressBarVisibility(boolean progressBarVisibility) {
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
        long now = System.currentTimeMillis();
        if (cityLatLngList.get(position).isExistInDb() || now - lastClickTime < Const.CLICK_TIME_INTERVAL) {
            return;
        }
        lastClickTime = now;
        locateMeCityLatLng = cityLatLngList.get(position);
        cityWeatherDataObservable.onNext(locateMeCityLatLng);
    }

    private void addCityToDatabase(City city) {
        setCityDataFromLocateMe(city);
        realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(city));
        searchViewDataListener.newCityAdded(city.getId());
    }

    public void startRxStream() {

        final WeatherApi serviceAutocomplete = retrofitAutocomplete.create(WeatherApi.class);

        final WeatherApi serviceDetails = retrofitDetails.create(WeatherApi.class);

        final WeatherApi serviceWeather = retrofitDarksky.create(WeatherApi.class);

        final WeatherApi serviceGeocode = retrofitGeocode.create(WeatherApi.class);

        cityDetailsDataObservable
                .debounce(100, TimeUnit.MILLISECONDS)
                .flatMap(s -> serviceGeocode.getCityFromLatLng(s, Const.KEY_LOCATION_TYPE, Const.KEY_RESULT_TYPE, Const.KEY_LNG, Const.GOOGLE_GEOCODING_KEY)
                        .doOnError(e -> showNoInternetError()))
                .flatMap(geocodeCity -> serviceDetails.getCityLatLng(geocodeCity.getResults().get(0).getPlaceId(), Const.KEY_LNG, Const.GOOGLE_PLACES_KEY)
                        .doOnError(e -> showNoInternetError()))
                .retry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityLatLng>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CityLatLng value) {
                        if (value != null) {
                            checkIfCityIsInDatabase(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onComplete(): ");
                    }
                });

        cityWeatherDataObservable
                .debounce(100, TimeUnit.MILLISECONDS)
                .flatMap(cityLatLng -> serviceWeather.getCity(getLat(cityLatLng), getLng(cityLatLng), Const.KEY_LNG, Const.KEY_EXCLUDE, Const.KEY_UNITS)
                        .doOnError(e -> showNoInternetError()))
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
                        if (value != null) addCityToDatabase(value);
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
                        return serviceAutocomplete.getCityName(s, Const.KEY_CITIES, Const.KEY_LNG, Const.GOOGLE_PLACES_KEY)
                                .onErrorResumeNext(throwable -> {
                                    checkErrorType();
                                    return Observable.empty();
                                })
                                .flatMapIterable(CityID::getPredictions)
                                .flatMap(prediction -> serviceDetails.getCityLatLng(prediction.getPlaceId(), Const.KEY_LNG, Const.GOOGLE_PLACES_KEY))
                                .onErrorResumeNext(throwable -> {
                                    checkErrorType();
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
                    public void onNext(List<CityLatLng> list) {
                        setProgressBarVisibility(false);
                        checkIfErrorOccured(list);
                        cityLatLngList = list;
                        searchViewDataListener.notifyAdapter(list);
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
        stopListeningForLocationChange();
    }

    private int getPrimaryKeyId(Class data) {
        int key;
        try {
            key = realm.where(data).max(Const.KEY_ID).intValue() + 1;
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            key = 0;
        }
        return key;
    }

    private String getLat(CityLatLng cityLatLng) {
        return cityLatLng.getResult().getGeometry().getLocation().getLat().toString();
    }

    private String getLng(CityLatLng cityLatLng) {
        return cityLatLng.getResult().getGeometry().getLocation().getLng().toString();
    }

    private Integer getLastSortedPosition() {
        RealmResults<City> cityList = realm.where(City.class).findAll();

        if (cityList.isEmpty()) {
            return 0;
        } else {
            City city = Collections.max(cityList, (city1, city2) -> city1.getSortPosition() - city2.getSortPosition());
            return city.getSortPosition() + 1;
        }
    }

    private void setCityDataFromLocateMe(City city) {
        city.setName(locateMeCityLatLng.getResult().getName());
        city.setAdressDescription(locateMeCityLatLng.getResult().getFormattedAddress());
        city.setRefreshDate(new SimpleDateFormat(Const.DATE_FORMAT, Locale.getDefault()).format(System.currentTimeMillis()));
        city.setPlaceId(locateMeCityLatLng.getResult().getPlaceId());
        city.setId(getPrimaryKeyId(City.class));
        city.setSortPosition(getLastSortedPosition());
        city.getCurrently().setId(getPrimaryKeyId(Currently.class));
        city.getDaily().setId(getPrimaryKeyId(Daily.class));
        city.getHourly().setId(getPrimaryKeyId(Hourly.class));

        int idDailyData = getPrimaryKeyId(DailyData.class);
        int idHourlyData = getPrimaryKeyId(HourlyData.class);

        for (DailyData data : city.getDaily().getData()) {
            data.setId(idDailyData);
            data.setPlaceId(city.getPlaceId());
            idDailyData++;
        }

        for (HourlyData data : city.getHourly().getData()) {
            data.setId(idHourlyData);
            data.setPlaceId(city.getPlaceId());
            idHourlyData++;
        }
    }

    public void hideKeyboard(View view) {
        searchViewDataListener.loseFocus();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String text = charSequence.toString();
        deleteDataFromList();

        if (text.equals("")) {
            setProgressBarVisibility(false);
        } else {
            setProgressBarVisibility(true);
            textWatcherObservable.onNext(charSequence.toString());
        }
    }

    public void locateMe() {
        if (locatingInProgress)
            return;

        searchViewDataListener.showToast(context.getString(R.string.locating_in_progress));
        locatingInProgress = true;
        deleteDataFromList();
        setProgressBarVisibility(true);

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        startLocationListener(locationManager);

    }

    private void showNoInternetError() {
        searchViewDataListener.showToast(context.getString(R.string.no_server_connection));
        resetErrorsState();
    }

    private void showNoCityFoundError() {
        searchViewDataListener.showToast(context.getString(R.string.city_not_found));
        resetErrorsState();
    }

    private void checkIfCityIsInDatabase(CityLatLng cityLatLng) {
        realm.executeTransaction(realm -> {
            City city = realm.where(City.class).equalTo(Const.KEY_PLACE_ID, cityLatLng.getResult().getPlaceId()).findFirst();
            if (city != null) {
                searchViewDataListener.showToast(context.getString(R.string.city_exists_in_db));
                setProgressBarVisibility(false);
            } else {
                locateMeCityLatLng = cityLatLng;
                cityWeatherDataObservable.onNext(cityLatLng);
            }
        });
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null) {
            if (netInfo.getTypeName().equalsIgnoreCase(Const.KEY_WIFI))
                if (netInfo.isConnected())
                    haveConnectedWifi = true;
            if (netInfo.getTypeName().equalsIgnoreCase(Const.KEY_MOBILE))
                if (netInfo.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void checkErrorType() {
        if (!haveNetworkConnection())
            internetConnectionError = true;
        else
            streamInterrupted = true;
    }

    private void checkIfErrorOccured(List<CityLatLng> value) {
        if (value.isEmpty() && internetConnectionError) {
            showNoInternetError();
            return;
        } else if (value.isEmpty() && !streamInterrupted) {
            showNoCityFoundError();
            return;
        }
    }

    public void stopListeningForLocationChange() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if(locationManager != null)
            locationManager.removeUpdates(locationListener);
        }
        locatingInProgress = false;
    }

    private void resetErrorsState() {
        setProgressBarVisibility(false);
        internetConnectionError = false;
        streamInterrupted = false;
    }

    private void deleteDataFromList() {
        cityLatLngList.clear();
        searchViewDataListener.notifyAdapter(cityLatLngList);
    }

    private void startLocationListener(LocationManager locationManager) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
    }
}
