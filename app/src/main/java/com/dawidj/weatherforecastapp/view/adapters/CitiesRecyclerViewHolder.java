package com.dawidj.weatherforecastapp.view.adapters;

import android.support.v7.widget.RecyclerView;

import com.dawidj.weatherforecastapp.databinding.CityModelBinding;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class CitiesRecyclerViewHolder extends RecyclerView.ViewHolder {

    private CityModelBinding binding;

    public CitiesRecyclerViewHolder(CityModelBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public CityModelBinding getBinding() {
        return binding;
    }
}
