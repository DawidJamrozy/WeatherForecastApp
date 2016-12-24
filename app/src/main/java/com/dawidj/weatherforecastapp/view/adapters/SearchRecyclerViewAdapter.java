package com.dawidj.weatherforecastapp.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.SearchModelBinding;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewHolder> {

    private List<CityLatLng> locationList;

    @Inject
    Realm realm;

    public SearchRecyclerViewAdapter(List<CityLatLng> locationList) {
        this.locationList = locationList;
        App.getApplication().getWeatherComponent().inject(this);
    }

    @Override
    public SearchRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchModelBinding binder = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.search_model, parent, false);
        return new SearchRecyclerViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(SearchRecyclerViewHolder holder, int position) {
        CityLatLng cityLatLng = locationList.get(position);
        holder.getBinding().setCityLatLng(cityLatLng);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public void setList(List<CityLatLng> list) {
        realm.executeTransaction(realm -> {
            for(CityLatLng latlng : list) {
                RealmResults<City> result = realm.where(City.class).equalTo("placeId", latlng.getResult().getPlaceId()).findAll();
                if(!result.isEmpty()) {
                    latlng.setExistInDb(true);
                }
            }
        });

        locationList = list;

        notifyDataSetChanged();
    }

}
