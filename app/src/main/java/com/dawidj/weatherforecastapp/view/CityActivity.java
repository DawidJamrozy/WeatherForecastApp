package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.CityFragmentBinding;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.SingleToast;
import com.dawidj.weatherforecastapp.utils.listeners.CityViewDataListener;
import com.dawidj.weatherforecastapp.view.adapters.DayRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.CityViewModel;

import org.parceler.Parcels;

import timber.log.Timber;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener, CityViewDataListener {

    private DayRecyclerViewAdapter dayRecyclerViewAdapter;
    private CityFragmentBinding binding;
    private CityViewModel cityViewModel;
    private City city;

    public CityActivity() {
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
        Bundle args = getArguments();
        city = Parcels.unwrap(args.getParcelable(Const.KEY_CITY));
        cityViewModel = new CityViewModel(city, this);
        binding.setCityViewModel(cityViewModel);
        binding.includeLayout.setCityViewModel(cityViewModel);
        App.getApplication().getWeatherComponent().inject(cityViewModel);
        setRecyclerView();
        cityViewModel.setCityName(city.getName());
        cityViewModel.setDayChart(binding.lineChart);
        cityViewModel.getWeatherData();
        cityViewModel.refreshWeatherData();
        dayRecyclerViewAdapter.notifyDataSetChanged();
        binding.swipeRefreshLayout.setOnRefreshListener(this);
        Timber.i("onCreateView(): ");
        return view;
    }

    @Override
    public void onRefresh() {
        Timber.i("onRefresh(): ");
        binding.swipeRefreshLayout.setRefreshing(true);
        cityViewModel.refreshData();
    }

    public void setRecyclerView() {
        dayRecyclerViewAdapter = new DayRecyclerViewAdapter(cityViewModel.getDayDatasList());
        binding.dayRecyclerView.setHasFixedSize(true);
        binding.dayRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        binding.dayRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.dayRecyclerView.setAdapter(dayRecyclerViewAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cityViewModel.onDestroy();
    }

    @Override
    public void notifyDataChanged() {
        cityViewModel.setDayChart(binding.lineChart);
        cityViewModel.getWeatherData();
        binding.swipeRefreshLayout.setRefreshing(false);
        dayRecyclerViewAdapter.notifyDataSetChanged();
        Timber.d("notifyDataChanged(): ");
    }

    @Override
    public void startMyCitiesActivity() {
        Timber.i("startMyCitiesActivity(): ");
        getActivity().startActivity(new Intent(getActivity(), MyCitiesViewActivity.class));
        getActivity().finish();
    }

    @Override
    public void turnOffSwipeToRefresh() {
        getActivity().runOnUiThread(() -> {
            SingleToast.show(getActivity(), "Brak połączenia z internetem", Toast.LENGTH_SHORT);
            binding.swipeRefreshLayout.setRefreshing(false);
        });
        Timber.d("turnOffSwipeToRefresh(): ");
    }
}
