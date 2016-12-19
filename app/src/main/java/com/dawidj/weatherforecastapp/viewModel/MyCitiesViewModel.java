package com.dawidj.weatherforecastapp.viewModel;

import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.models.dbtest.DailyData;
import com.dawidj.weatherforecastapp.models.dbtest.HourlyData;
import com.dawidj.weatherforecastapp.view.adapters.DeleteItem;
import com.dawidj.weatherforecastapp.view.adapters.NotifyAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class MyCitiesViewModel implements DeleteItem {

    private NotifyAdapter notifyAdapter;

    private List<City> cityList = new ArrayList<>();

    public List<City> getCityList() {
        return cityList;
    }

    public void setNotifyAdapter(NotifyAdapter notifyAdapter) {
        this.notifyAdapter = notifyAdapter;
    }

    @Inject
    Realm realm;

    public MyCitiesViewModel() {
    }


    @Override
    public void deleteCityFromList(City city) {

        int position = cityList.indexOf(city);

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
        // TODO: 19.12.2016 Realm is not deleting inside class data
        if (notifyAdapter != null) {
            notifyAdapter.notifyAdapter(position);
            cityList.remove(position);
        }
    }
}
