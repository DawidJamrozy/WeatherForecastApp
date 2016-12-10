package com.dawidj.weatherforecastapp.view.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.databinding.CityModelBinding;
import com.dawidj.weatherforecastapp.models.Weather.City;

import java.util.List;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class CitiesRecycerViewAdapter extends RecyclerView.Adapter<CitiesRecyclerViewHolder> {

    private List<City> cityList;

    private LayoutInflater layoutInflater;

    private Context context;

    public CitiesRecycerViewAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public CitiesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityModelBinding binder = DataBindingUtil.inflate(layoutInflater, R.layout.city_model, parent, false);
        return new CitiesRecyclerViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(CitiesRecyclerViewHolder holder, int position) {
        City city = cityList.get(position);
        holder.getBinding().setCityModel(city);
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}
