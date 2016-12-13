package com.dawidj.weatherforecastapp.view.adapters;

import android.content.Context;
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

    private LayoutInflater inflater;

    private Context context;

    public SearchRecyclerViewAdapter(Context context, List<CityLatLng> locationList) {
        this.context = context;
        this.locationList = locationList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public SearchRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LocationModelBinding binder = DataBindingUtil.inflate(inflater, R.layout.location_model, parent, false);
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
        this.locationList = list;
        notifyDataSetChanged();
    }
}
