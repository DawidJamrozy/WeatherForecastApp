package com.dawidj.weatherforecastapp.view.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.databinding.DayModelBinding;
import com.dawidj.weatherforecastapp.models.City;
import com.dawidj.weatherforecastapp.models.DailyData;

import java.util.List;

/**
 * Created by djamrozy on 02.12.2016.
 */

public class DayRecyclerViewAdapter extends RecyclerView.Adapter<DayRecyclerViewHolder> implements DisplayCityView {

    List<DailyData> dailyList;

    private LayoutInflater inflater;

    public DayRecyclerViewAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public DayRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DayModelBinding binder = DataBindingUtil.inflate(inflater, R.layout.day_model, parent, false);
        return new DayRecyclerViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(DayRecyclerViewHolder holder, int position) {
        DailyData daily = dailyList.get(position);
        holder.getBinding().setDailyData(daily);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public void displayCities(City city) {

    }

    @Override
    public void displayDailyList(List<DailyData> dailyList) {
        this.dailyList = dailyList;
        notifyDataSetChanged();
    }
}
