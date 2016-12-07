package com.dawidj.weatherforecastapp.view.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.databinding.DayModelBinding;
import com.dawidj.weatherforecastapp.models.DayData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djamrozy on 02.12.2016.
 */

public class DayRecyclerViewAdapter extends RecyclerView.Adapter<DayRecyclerViewHolder> implements DisplayDayView {

    List<DayData> dayDataList = new ArrayList<>();
    private LayoutInflater inflater;

    Context context;

    public DayRecyclerViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public DayRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DayModelBinding binder = DataBindingUtil.inflate(inflater, R.layout.day_model, parent, false);
        return new DayRecyclerViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(DayRecyclerViewHolder holder, int position) {
        DayData dayData = dayDataList.get(position);
        holder.getBinding().setDay(dayData);

    }

    @Override
    public int getItemCount() {
        return dayDataList.size();
    }

    @Override
    public void displayDayList(List<DayData> dayDataList) {
        this.dayDataList = dayDataList;
        notifyDataSetChanged();
    }
}
