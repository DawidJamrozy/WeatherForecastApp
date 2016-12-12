package com.dawidj.weatherforecastapp.viewModel;

import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;

import com.dawidj.weatherforecastapp.api.WeatherApi;
import com.dawidj.weatherforecastapp.models.autocomplete.CityID;
import com.dawidj.weatherforecastapp.models.autocomplete.Prediction;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.models.dbtest.DailyData;
import com.dawidj.weatherforecastapp.models.dbtest.DaoSession;
import com.dawidj.weatherforecastapp.models.dbtest.HourlyData;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.busevent.AddLocation;
import com.dawidj.weatherforecastapp.utils.busevent.ClearLocation;
import com.dawidj.weatherforecastapp.utils.busevent.NotifyRecyclerAdapterEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class SearchViewModel {

    private List<CityLatLng> cityLatLngList = new ArrayList<>();
    private String name = "Location List";
    private ObservableField<String> cityName = new ObservableField<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final PublishSubject<String> textWatcherObservable = PublishSubject.create();
    private final PublishSubject<CityLatLng> cityWeatherDataObservable = PublishSubject.create();
    private int listPosition;

    public ObservableField<String> getCityName() {
        return cityName;
    }

    public String getName() {
        return name;
    }

    public List<CityLatLng> getCityLatLngList() {
        return cityLatLngList;
    }

    @Inject
    EventBus eventBus;

    @Inject
    DaoSession daoSession;

    @Inject
    @Named("darksky")
    Retrofit retrofitDarksky;

    @Inject
    @Named("autocomplete")
    Retrofit retrofitAutocomplete;

    @Inject
    @Named("details")
    Retrofit retrofitDetails;

    public SearchViewModel() {

    }
    //klikniecie na recycler view wywołuje tą metode
    public void addCity(final int position) {
        Timber.i("addCity(): " + position);
        listPosition = position;
        cityWeatherDataObservable.onNext(cityLatLngList.get(position));
    }

    public void testRx() {
        Timber.i("testRx(): ");

    }

    public void rxQueryBuilder() {
        final WeatherApi serviceAutocomplete = retrofitAutocomplete.create(WeatherApi.class);

        final WeatherApi serviceDetails = retrofitDetails.create(WeatherApi.class);

        final WeatherApi serviceWeather = retrofitDarksky.create(WeatherApi.class);

        cityWeatherDataObservable
                .flatMap(new Function<CityLatLng, ObservableSource<City>>() {
                    @Override
                    public ObservableSource<City> apply(CityLatLng cityLatLng) throws Exception {
                        return serviceWeather.getCity(cityLatLng.getResult().getGeometry().getLocation().getLat().toString(),
                                cityLatLng.getResult().getGeometry().getLocation().getLng().toString(), "pl", "flags,alerts,minutely", "ca");


                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<com.dawidj.weatherforecastapp.models.dbtest.City>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(com.dawidj.weatherforecastapp.models.dbtest.City value) {
                        value.setName(cityLatLngList.get(listPosition).getResult().getName());

                        long id = daoSession.getCityDao().insert(value);

                        for(HourlyData hourlyData : value.getHourly().getData()) {
                            hourlyData.setHourlyID(id);
                            daoSession.getHourlyDataDao().insert(hourlyData);
                        }
                        for (DailyData dailyData : value.getDaily().getData()) {
                            dailyData.setDailyID(id);
                            daoSession.getDailyDataDao().insert(dailyData);
                        }
                        Timber.i("onNext(): ");
                        daoSession.getCurrentlyDao().insert(value.getCurrently());
                        daoSession.getDailyDao().insert(value.getDaily());
                        daoSession.getHourlyDao().insert(value.getHourly());
                        eventBus.post(new NotifyRecyclerAdapterEvent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        textWatcherObservable
                .debounce(800, TimeUnit.MILLISECONDS)
                .doOnEach(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {compositeDisposable.add(d);}

                    @Override
                    public void onNext(String value) {
                        if (!cityLatLngList.isEmpty()) {
                            cityLatLngList.clear();
                            eventBus.post(new ClearLocation());
                        }
                        Timber.i("onNext(): ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {}
                })
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Exception {
                        return !s.isEmpty();
                    }
                })
                .flatMap(new Function<String, ObservableSource<CityID>>() {
                    @Override
                    public ObservableSource<CityID> apply(String s) throws Exception {
                        return serviceAutocomplete.getCityName(s, "(cities)", Const.GOOGLE_PLACES_KEY);
                    }
                })
                .flatMapIterable(new Function<CityID, Iterable<Prediction>>() {
                    @Override
                    public Iterable<Prediction> apply(CityID cityID) throws Exception {
                        return cityID.getPredictions();
                    }
                }).flatMap(new Function<Prediction, ObservableSource<CityLatLng>>() {
            @Override
            public ObservableSource<CityLatLng> apply(Prediction prediction) throws Exception {
                return serviceDetails.getCityLatLng(prediction.getPlaceId(), Const.GOOGLE_PLACES_KEY);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CityLatLng>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(CityLatLng value) {
                        cityLatLngList.add(value);
                        eventBus.post(new AddLocation());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {}
                });
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
        };
    }

    public void onDestroy() {
        compositeDisposable.clear();
    }

}
