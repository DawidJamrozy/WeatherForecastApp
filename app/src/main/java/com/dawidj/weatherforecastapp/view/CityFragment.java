package com.dawidj.weatherforecastapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.CityFragmentBinding;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.eventbus.ChangeListener;
import com.dawidj.weatherforecastapp.view.adapters.DayRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.CityViewModel;
import com.github.mikephil.charting.charts.LineChart;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ChangeListener {

    @BindView(R.id.linechart)
    LineChart lineChart;
    @BindView(R.id.dayrecyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private DayRecyclerViewAdapter dayRecyclerViewAdapter;
    private CityFragmentBinding binding;
    private CityViewModel cityViewModel;
    private City city;

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
        Bundle args = getArguments();
        city = Parcels.unwrap(args.getParcelable(Const.KEY_CITY));
        cityViewModel = new CityViewModel(city);
        binding.setCityViewModel(cityViewModel);
        binding.includelayout.setCityViewModel(cityViewModel);
        App.getApplication().getWeatherComponent().inject(cityViewModel);
        cityViewModel.setChangeListener(this);
        setRecyclerView();
        cityViewModel.setCityName(city.getName());
        cityViewModel.setDayChart(lineChart);
        cityViewModel.getWeatherData();
        cityViewModel.refreshWeatherData();
        dayRecyclerViewAdapter.notifyDataSetChanged();
        swipeRefreshLayout.setOnRefreshListener(this);
        Timber.i("onCreateView(): ");
        return view;
    }

    @Override
    public void onRefresh() {
        Timber.i("onRefresh(): ");
        swipeRefreshLayout.setRefreshing(true);
        cityViewModel.refreshData();
    }

    public void setRecyclerView() {
        dayRecyclerViewAdapter = new DayRecyclerViewAdapter(cityViewModel.getDayDatasList());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(dayRecyclerViewAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cityViewModel.onDestroy();
    }

    @Override
    public void change() {
        cityViewModel.setDayChart(lineChart);
        cityViewModel.getWeatherData();
        swipeRefreshLayout.setRefreshing(false);
        dayRecyclerViewAdapter.notifyDataSetChanged();
        Timber.i("refresh(): ");
    }
}
