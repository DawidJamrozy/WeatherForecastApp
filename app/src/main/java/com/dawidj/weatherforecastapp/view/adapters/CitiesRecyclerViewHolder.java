package com.dawidj.weatherforecastapp.view.adapters;

import android.support.v7.widget.RecyclerView;

import com.dawidj.weatherforecastapp.databinding.MyCitiesModelBinding;

/**
 * Created by Dawidj on 18.12.2016.
 */

public class CitiesRecyclerViewHolder extends RecyclerView.ViewHolder {

    private MyCitiesModelBinding binding;

    public CitiesRecyclerViewHolder(MyCitiesModelBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public MyCitiesModelBinding getBinding() {
        return binding;
    }
}
