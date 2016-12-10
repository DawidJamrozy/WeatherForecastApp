package com.dawidj.weatherforecastapp.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dawidj.weatherforecastapp.databinding.LocationModelBinding;

import timber.log.Timber;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class SearchRecyclerViewHolder extends RecyclerView.ViewHolder {

    private LocationModelBinding binding;

    public SearchRecyclerViewHolder(final LocationModelBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                Timber.i("onClick(): " + binding.getCityLatLng().getResult().getPlaceId());
                Timber.i("onClick(): " + position);
            }
        });
    }

    public LocationModelBinding getBinding() {
        return binding;
    }
}
