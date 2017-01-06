package com.dawidj.weatherforecastapp.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.R;
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

import static com.dawidj.weatherforecastapp.utils.Const.CLICK_TIME_INTERVAL;
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
    private CityLatLng locateMeCityLatLng;
    private boolean internetConnectionError;
    private boolean streamInterrupted;
    private long lastClickTime;
    private boolean locatingInProgress;

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
        long now = System.currentTimeMillis();
        if (cityLatLngList.get(position).isExistInDb()) {
            return;
        }
        if (now - lastClickTime < CLICK_TIME_INTERVAL) {
            Timber.d("insertCityFromLocateMe(): click");
            return;
        }
        Timber.d("addCity(): click accepted");
        lastClickTime = now;
        locateMeCityLatLng = cityLatLngList.get(position);
        cityWeatherDataObservable.onNext(cityLatLngList.get(position));
    }

    public void insertCityFromLocateMe(City city) {
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
                .flatMap(s -> serviceGeocode.getCityFromLatLng(s, KEY_LOCATION_TYPE, KEY_RESULT_TYPE, KEY_LNG, GOOGLE_GEOCODING_KEY)
                        .doOnError(e -> showNoInternetError()))
                .flatMap(geocodeCity -> serviceDetails.getCityLatLng(geocodeCity.getResults().get(0).getPlaceId(), KEY_LNG, GOOGLE_PLACES_KEY)
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
                        Timber.d("onNext(): ");
                        if (value != null) {
                            checkDb(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        cityWeatherDataObservable
                .debounce(100, TimeUnit.MILLISECONDS)
                .flatMap(cityLatLng -> serviceWeather.getCity(getLat(cityLatLng), getLng(cityLatLng), KEY_LNG, KEY_EXCLUDE, KEY_UNITS)
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
                        if (value != null) insertCityFromLocateMe(value);
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
                                    checkErrorType();
                                    Timber.d("apply(): error 1");
                                    return Observable.empty();
                                })
                                .flatMapIterable(cityID -> cityID.getPredictions())
                                .flatMap(prediction -> serviceDetails.getCityLatLng(prediction.getPlaceId(), KEY_LNG, GOOGLE_PLACES_KEY))
                                .onErrorResumeNext(throwable -> {
                                    checkErrorType();
                                    Timber.d("apply(): error2");
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
                        setProgressBarVisibility(false);
                        checkIfErrorOccured(value);
                        cityLatLngList = value;
                        searchViewDataListener.notifyAdapter(value);
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


    public void setCityDataFromLocateMe(City city) {
        String name = locateMeCityLatLng.getResult().getName();

        city.setName(name);
        city.setAdressDescription(locateMeCityLatLng.getResult().getFormattedAddress());
        city.setRefreshDate(new SimpleDateFormat(DATE_FORMAT).format(System.currentTimeMillis()));
        city.setPlaceId(locateMeCityLatLng.getResult().getPlaceId());
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

    public void locateMe() {
        if(locatingInProgress)
            return;

        locatingInProgress = true;
        cityLatLngList.clear();
        searchViewDataListener.notifyAdapter(cityLatLngList);
        setProgressBarVisibility(true);
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                String lat = Double.toString(location.getLatitude());
                String lng = Double.toString(location.getLongitude());
                Timber.d("onLocationChanged(): ");
                cityDetailsDataObservable.onNext(lat + "," + lng);
                locationManager.removeUpdates(this);
                locatingInProgress = false;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
                Timber.d("onProviderEnabled(): enabled");
            }

            @Override
            public void onProviderDisabled(String provider) {
                Timber.d("onProviderDisabled(): disabled");
                locationManager.removeUpdates(this);
                setProgressBarVisibility(false);
            }
        });
    }

    public void showNoInternetError() {
        searchViewDataListener.showToast(context.getString(R.string.no_server_connection));
        setProgressBarVisibility(false);
        internetConnectionError = false;
        streamInterrupted = false;
    }

    public void showNoCityFoundError() {
        searchViewDataListener.showToast(context.getString(R.string.city_not_found));
        internetConnectionError = false;
        streamInterrupted = false;
    }

    public void checkDb(CityLatLng cityLatLng) {
        realm.executeTransaction(realm -> {
            City city = realm.where(City.class).equalTo("placeId", cityLatLng.getResult().getPlaceId()).findFirst();
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
            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (netInfo.isConnected())
                    haveConnectedWifi = true;
            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
                if (netInfo.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    public void checkErrorType() {
        if (!haveNetworkConnection())
            internetConnectionError = true;
        else
            streamInterrupted = true;
    }

    public void checkIfErrorOccured(List<CityLatLng> value) {
        if (value.isEmpty() && internetConnectionError) {
            showNoInternetError();
            return;
        } else if (value.isEmpty() && !streamInterrupted) {
            showNoCityFoundError();
            return;
        }
    }

}
