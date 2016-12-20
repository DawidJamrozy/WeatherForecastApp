package com.dawidj.weatherforecastapp.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.databinding.LocationModelBinding;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;

import java.util.List;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewHolder> {

    private List<CityLatLng> locationList;

    public SearchRecyclerViewAdapter(List<CityLatLng> locationList) {
        this.locationList = locationList;
    }

    @Override
    public SearchRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LocationModelBinding binder = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.location_model, parent, false);
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
        locationList = list;
        notifyDataSetChanged();
    }

}
