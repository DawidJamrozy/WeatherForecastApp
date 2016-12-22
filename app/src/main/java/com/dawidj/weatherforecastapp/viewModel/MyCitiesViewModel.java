package com.dawidj.weatherforecastapp.viewModel;

import android.databinding.BaseObservable;
import android.view.View;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.models.dbtest.DailyData;
import com.dawidj.weatherforecastapp.models.dbtest.HourlyData;
import com.dawidj.weatherforecastapp.utils.listeners.MyCitiesViewDataListener;
import com.dawidj.weatherforecastapp.view.adapters.DeleteItem;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class MyCitiesViewModel extends BaseObservable implements DeleteItem {

    private boolean textVisible;
    private List<City> cityList = new ArrayList<>();
    private MyCitiesViewDataListener myCitiesViewDataListener;

    public boolean isTextVisible() {
        return textVisible;
    }

    public void setTextVisible(boolean textVisible) {
        this.textVisible = textVisible;
        notifyPropertyChanged(BR._all);
    }

    public List<City> getCityList() {
        return cityList;
    }

    @Inject
    Realm realm;

    public MyCitiesViewModel(MyCitiesViewDataListener myCitiesViewDataListener) {
        this.myCitiesViewDataListener = myCitiesViewDataListener;
    }

    @Override
    public void checkIfListIsEmpty() {
        if (cityList.isEmpty()) {
            setTextVisible(true);
        } else {
            setTextVisible(false);
        }
    }

    @Override
    public void deleteCityFromList(City city) {

        int position = cityList.indexOf(city);
        Timber.d("deleteCityFromList(): placeID " + city.getPlaceId());
        Timber.d("deleteCityFromList(): name " + city.getName());
        realm.executeTransaction(realm1 -> {
            for (DailyData data : realm.where(DailyData.class).equalTo("mainId", city.getId()).findAll()) {
                data.deleteFromRealm();
            }

            for (HourlyData data : realm.where(HourlyData.class).equalTo("mainId", city.getId()).findAll()) {
                data.deleteFromRealm();
            }

            city.getDaily().deleteFromRealm();
            city.getHourly().deleteFromRealm();
            city.getCurrently().deleteFromRealm();
            city.deleteFromRealm();
        });

        myCitiesViewDataListener.removeCity(position);
        cityList.remove(position);
        checkIfListIsEmpty();
    }


    public void fabClicked(View view) {
        myCitiesViewDataListener.onClickFab();
    }
}
