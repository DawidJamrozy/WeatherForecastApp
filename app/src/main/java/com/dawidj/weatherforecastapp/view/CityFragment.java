package com.dawidj.weatherforecastapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.CityFragmentBinding;
import com.dawidj.weatherforecastapp.utils.AxisValueFormatter;
import com.dawidj.weatherforecastapp.utils.busevent.LineChartEvent;
import com.dawidj.weatherforecastapp.utils.busevent.NotifyRecyclerAdapterEvent;
import com.dawidj.weatherforecastapp.view.adapters.DayRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.CityViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityFragment extends Fragment {

    @BindView(R.id.linechart)
    LineChart lineChart;
    @BindView(R.id.dayrecyclerview)
    RecyclerView recyclerView;

    private DayRecyclerViewAdapter dayRecyclerViewAdapter;
    private CityFragmentBinding binding;
    private CityViewModel cityViewModel;
    private String cityName;

    @Inject
    EventBus eventBus;

    public CityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.city_fragment, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        cityViewModel = new CityViewModel(getActivity());
        binding.setCityViewModel(cityViewModel);
        binding.includelayout.setCityViewModel(cityViewModel);
        App.getApplication().getWeatherComponent().inject(cityViewModel);
        App.getApplication().getWeatherComponent().inject(this);
        setRecyclerView();
        cityViewModel.setDisplayDayView(dayRecyclerViewAdapter);
        Bundle args = getArguments();
        cityName = args.getString("cityName");
        cityViewModel.setCityName(cityName);
        //cityViewModel.getWeatherData();
        return view;
        //TODO Screen is moving to the middle - BUG
    }

    public void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        dayRecyclerViewAdapter = new DayRecyclerViewAdapter(getActivity());
        recyclerView.setAdapter(dayRecyclerViewAdapter);
    }

    @Subscribe
    public void onLineChartEvent(LineChartEvent event) {
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(event.minTemp);
        leftAxis.setAxisMaximum(event.maxTemp);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisMinimum(event.minTemp);
        rightAxis.setAxisMaximum(event.maxTemp);

        XAxis downAxis = lineChart.getXAxis();
        downAxis.setLabelCount(25);
        downAxis.setDrawGridLines(false);
        downAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        downAxis.setValueFormatter(new AxisValueFormatter());

        Description description = new Description();
        description.setText("");
        LineData lineData = new LineData(event.lineDataSet);
        lineChart.setData(lineData);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setDescription(description);
        lineChart.canScrollHorizontally(1);
        lineChart.invalidate();
    }

    @Subscribe
    public void onNotifyRecyclerAdapter(NotifyRecyclerAdapterEvent event) {
        Timber.i("onNotifyRecyclerAdapter(): invoked");
        dayRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.getDefault().unregister(this);
    }
}
