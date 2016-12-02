package com.dawidj.weatherforecastapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.CityFragmentBinding;
import com.dawidj.weatherforecastapp.utils.AxisValueFormatter;
import com.dawidj.weatherforecastapp.utils.LineChartEvent;
import com.dawidj.weatherforecastapp.utils.NotifyRecyclerAdapter;
import com.dawidj.weatherforecastapp.view.adapters.DayRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.CityViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityViewFragment extends Fragment {

    @BindView(R.id.linechart)
    LineChart lineChart;

    private CityFragmentBinding binding;
    private CityViewModel cityViewModel;

    @Inject
    EventBus eventBus;

    public CityViewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private DayRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.city_fragment, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        cityViewModel = new CityViewModel(getActivity());
        binding.setCityViewModel(cityViewModel);
        App.getApplication().getWeatherComponent().inject(cityViewModel);
        App.getApplication().getWeatherComponent().inject(this);

        cityViewModel.setDisplayCityView(adapter);
        return view;
    }

    @OnClick(R.id.button)
    public void loadChart() {
        cityViewModel.getWeatherData();
    }

    @Subscribe
    public void onLineChartEvent(LineChartEvent event) {
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawLabels(false);
        rightAxis.setAxisMinimum(0f);

        XAxis downAxis = lineChart.getXAxis();
        downAxis.setLabelCount(25);
        downAxis.setDrawGridLines(false);
        downAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        downAxis.setValueFormatter(new AxisValueFormatter());

        LineData lineData = new LineData(event.lineDataSet);
        lineChart.setData(lineData);
        lineChart.getLegend().setEnabled(false);
        lineChart.setTouchEnabled(false);
        lineChart.setDescription(event.description);
        lineChart.canScrollHorizontally(1);
        lineChart.invalidate();
    }

    @Subscribe
    public void onNotifyRecyclerAdapter(NotifyRecyclerAdapter event) {
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        //eventBus.unregister(this);
        EventBus.getDefault().unregister(this);
    }
}
