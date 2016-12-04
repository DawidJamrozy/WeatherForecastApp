package com.dawidj.weatherforecastapp.view.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.databinding.LocationModelBinding;
import com.dawidj.weatherforecastapp.models.Location;

import java.util.List;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class LocationRecyclerViewAdapter extends RecyclerView.Adapter<LocationRecyclerViewHolder>  {

    private List<Location> locationList;

    private LayoutInflater inflater;

    private Context context;

    public LocationRecyclerViewAdapter(Context context, List<Location> locationList) {
        this.context = context;
        this.locationList = locationList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public LocationRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LocationModelBinding binder = DataBindingUtil.inflate(inflater, R.layout.location_model, parent, false);
        return new LocationRecyclerViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(LocationRecyclerViewHolder holder, int position) {
        Location location = locationList.get(position);
        holder.getBinding().setLocation(location);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }
}
