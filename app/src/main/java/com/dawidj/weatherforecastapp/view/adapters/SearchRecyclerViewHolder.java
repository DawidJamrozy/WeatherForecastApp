package com.dawidj.weatherforecastapp.view.adapters;

import android.support.v7.widget.RecyclerView;

import com.dawidj.weatherforecastapp.databinding.LocationModelBinding;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class SearchRecyclerViewHolder extends RecyclerView.ViewHolder {

    private LocationModelBinding binding;

    public SearchRecyclerViewHolder(final LocationModelBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public LocationModelBinding getBinding() {
        return binding;
    }
}
