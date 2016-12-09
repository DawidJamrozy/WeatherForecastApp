package com.dawidj.weatherforecastapp.viewModel;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

import com.dawidj.weatherforecastapp.api.WeatherApi;
import com.dawidj.weatherforecastapp.models.City;
import com.dawidj.weatherforecastapp.models.CityID;
import com.dawidj.weatherforecastapp.models.CityLatLng;
import com.dawidj.weatherforecastapp.models.Location;
import com.dawidj.weatherforecastapp.utils.busevent.LocationEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Retrofit;

import static android.R.attr.value;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class LocationViewModel {

    private List<Location> locationList = new ArrayList<>();
    private List<CityLatLng> cityLatLngList = new ArrayList<>();
    private String name = "Location List";
    private ObservableField<String> cityName = new ObservableField<>();

    public ObservableField<String> getCityName() {
        return cityName;
    }

    public String getName() {
        return name;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    private final PublishSubject<String> textWatcherObservable = PublishSubject.create();

    @Inject
    EventBus eventBus;

    @Inject
    @Named("darksky")
    Retrofit retrofitDarksky;

    @Inject
    @Named("autocomplete")
    Retrofit retrofitAutocomplete;

    @Inject
    @Named("details")
    Retrofit retrofitDetails;


    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public LocationViewModel() {

        final WeatherApi serviceAutocomplete = retrofitAutocomplete.create(WeatherApi.class);

        final WeatherApi serviceDetails = retrofitDetails.create(WeatherApi.class);

        textWatcherObservable
                .debounce(300, TimeUnit.MILLISECONDS)
                // Zmienia flow elementów
                .flatMap(new Function<String, ObservableSource<CityID>>() {
                    @Override
                    public ObservableSource<CityID> apply(String name) throws Exception {
                        return serviceAutocomplete.getCityName(name);
                    }
                })
                .flatMap(new Function<CityID, ObservableSource<CityLatLng>>() {
                    @Override
                    public ObservableSource<CityLatLng> apply(CityID cityID) throws Exception {
                        return serviceDetails.getCityLatLng(cityID.getPlaceID());
                    }
                })
                // to co wyżej subscribeOn, wykonuje się w podanym wątku, wywołać tylko jeden raz
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CityLatLng>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CityLatLng value) {
                        cityLatLngList.add(value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        eventBus.post(new LocationEvent());
                    }
                });
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }

    public void addItem() {
        Location location = new Location();
        location.setName(getCityName().get());
        locationList.add(location);
        eventBus.post(new LocationEvent());
        //TODO clear edit text after adding new item
    }

    public TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                textWatcherObservable.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        }
    }
}
