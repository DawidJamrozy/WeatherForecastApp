package com.dawidj.weatherforecastapp.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.models.dbtest.City;

import java.util.List;

/**
 * Created by Dawidj on 18.12.2016.
 */

public class RecyclerHolder extends RecyclerView.Adapter<Holder> {

    private List<City> cityList;

    public RecyclerHolder(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_model, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        City city = cityList.get(position);
        holder.getBinding().setVariable(BR._all, city);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }
}