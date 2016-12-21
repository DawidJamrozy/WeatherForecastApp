package com.dawidj.weatherforecastapp.view.adapters;

import android.support.v7.widget.RecyclerView;

import com.dawidj.weatherforecastapp.databinding.SearchModelBinding;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class SearchRecyclerViewHolder extends RecyclerView.ViewHolder {

    private SearchModelBinding binding;

    public SearchRecyclerViewHolder(final SearchModelBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public SearchModelBinding getBinding() {
        return binding;
    }
}
