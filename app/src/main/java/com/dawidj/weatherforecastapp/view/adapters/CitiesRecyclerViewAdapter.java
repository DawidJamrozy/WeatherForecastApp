package com.dawidj.weatherforecastapp.view.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.databinding.CityModelBinding;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.utils.ItemTouchHelperAdapter;

import java.util.Collections;
import java.util.List;

import timber.log.Timber;

/**
 * Created by Dawidj on 18.12.2016.
 */

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<CitiesRecyclerViewHolder> implements ItemTouchHelperAdapter {

    private List<City> cityList;

    private DeleteItem deleteItem;

    public CitiesRecyclerViewAdapter(List<City> cityList, DeleteItem deleteItem) {
        this.cityList = cityList;
        this.deleteItem = deleteItem;
    }

    @Override
    public CitiesRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityModelBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.city_model, parent, false);
        return new CitiesRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CitiesRecyclerViewHolder citiesRecyclerViewHolder, int position) {
        City city = cityList.get(position);
        citiesRecyclerViewHolder.getBinding().setCity(city);
        citiesRecyclerViewHolder.getBinding().setDeleteCity(deleteItem);
        citiesRecyclerViewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cityList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cityList, i, i - 1);
            }
        }
        Timber.i("onItemMove(): from " + fromPosition);
        Timber.i("onItemMove(): to " + toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        cityList.remove(position);
        notifyItemRemoved(position);
    }
}