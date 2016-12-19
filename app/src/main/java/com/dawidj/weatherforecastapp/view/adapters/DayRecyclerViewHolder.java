package com.dawidj.weatherforecastapp.view.adapters;

import android.support.v7.widget.RecyclerView;

import com.dawidj.weatherforecastapp.databinding.DayModelBinding;

/**
 * Created by djamrozy on 02.12.2016.
 */

public class DayRecyclerViewHolder extends RecyclerView.ViewHolder {

    private DayModelBinding binding;

    public DayRecyclerViewHolder(DayModelBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public DayModelBinding getBinding() {
        return binding;
    }
}
