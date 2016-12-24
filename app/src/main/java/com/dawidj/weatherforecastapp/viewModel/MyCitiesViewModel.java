package com.dawidj.weatherforecastapp.viewModel;

import android.databinding.BaseObservable;
import android.view.View;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.models.darksky.DailyData;
import com.dawidj.weatherforecastapp.models.darksky.HourlyData;
import com.dawidj.weatherforecastapp.utils.listeners.MyCitiesViewDataListener;
import com.dawidj.weatherforecastapp.view.adapters.MyCitiesDataListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

import static com.dawidj.weatherforecastapp.utils.Const.KEY_PLACE_ID;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class MyCitiesViewModel extends BaseObservable implements MyCitiesDataListener {

    private boolean textVisible;
    private List<City> cityList = new ArrayList<>();
    private MyCitiesViewDataListener myCitiesViewDataListener;
    private long removeItemButtonClicked;

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
        if (System.currentTimeMillis() - removeItemButtonClicked < 1000) return;

        removeItemButtonClicked = System.currentTimeMillis();

        int position = cityList.indexOf(city);
        cityList.remove(position);
        myCitiesViewDataListener.removeCity(position);
        checkIfListIsEmpty();

        realm.executeTransaction(realm1 -> {
            for (DailyData data : realm.where(DailyData.class).equalTo(KEY_PLACE_ID, city.getPlaceId()).findAll()) {
                data.deleteFromRealm();
            }

            for (HourlyData data : realm.where(HourlyData.class).equalTo(KEY_PLACE_ID, city.getPlaceId()).findAll()) {
                data.deleteFromRealm();
            }

            city.getDaily().deleteFromRealm();
            city.getHourly().deleteFromRealm();
            city.getCurrently().deleteFromRealm();
            city.deleteFromRealm();
        });

    }

    public void fabClicked(View view) {
        myCitiesViewDataListener.onClickFab();
    }
}
