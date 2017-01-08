package com.dawidj.weatherforecastapp.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.MyCitiesModelBinding;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.models.darksky.DailyData;
import com.dawidj.weatherforecastapp.models.darksky.HourlyData;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.ItemTouchHelperAdapter;
import com.dawidj.weatherforecastapp.utils.listeners.MyCitiesDataListener;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Dawidj on 18.12.2016.
 */

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<CitiesRecyclerViewHolder> implements ItemTouchHelperAdapter {

    private List<City> cityList;

    private MyCitiesDataListener myCitiesDataListener;

    @Inject
    Realm realm;

    public CitiesRecyclerViewAdapter(List<City> cityList, MyCitiesDataListener myCitiesDataListener) {
        this.cityList = cityList;
        this.myCitiesDataListener = myCitiesDataListener;
        App.getApplication().getWeatherComponent().inject(this);
    }

    @Override
    public CitiesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyCitiesModelBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.my_cities_model, parent, false);
        return new CitiesRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CitiesRecyclerViewHolder citiesRecyclerViewHolder, int position) {
        City city = cityList.get(position);
        citiesRecyclerViewHolder.getBinding().setCity(city);
        citiesRecyclerViewHolder.getBinding().setDeleteCity(myCitiesDataListener);
        citiesRecyclerViewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cityList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cityList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);

        realm.executeTransaction(realm1 -> {
            for(City city : cityList) {
                city.setSortPosition(cityList.indexOf(city));
            }
            realm1.copyToRealmOrUpdate(cityList);
        });
    }

    @Override
    public void onItemDismiss(int position) {
        City city = cityList.get(position);
        realm.executeTransaction(realm1 -> {
            for (DailyData data : realm.where(DailyData.class).equalTo(Const.KEY_PLACE_ID, city.getPlaceId()).findAll()) {
                data.deleteFromRealm();
            }

            for (HourlyData data : realm.where(HourlyData.class).equalTo(Const.KEY_PLACE_ID, city.getPlaceId()).findAll()) {
                data.deleteFromRealm();
            }

            city.getDaily().deleteFromRealm();
            city.getHourly().deleteFromRealm();
            city.getCurrently().deleteFromRealm();
            city.deleteFromRealm();
        });
        cityList.remove(position);
        notifyItemRemoved(position);
        myCitiesDataListener.checkIfListIsEmpty();

    }
}