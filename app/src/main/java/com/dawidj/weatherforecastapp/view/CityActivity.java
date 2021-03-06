package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
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
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.SingleToast;
import com.dawidj.weatherforecastapp.utils.listeners.CityViewDataListener;
import com.dawidj.weatherforecastapp.view.adapters.DayRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.CityViewModel;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityActivity extends Fragment implements SwipeRefreshLayout.OnRefreshListener, CityViewDataListener {

    private DayRecyclerViewAdapter dayRecyclerViewAdapter;
    private CityFragmentBinding binding;
    private CityViewModel cityViewModel;
    private City city;
    private SingleToast singleToast = new SingleToast();

    @Inject
    Realm realm;

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
        App.getApplication().getWeatherComponent().inject(this);
        selectCityFromDatabase();
        cityViewModel = new CityViewModel(city, this);
        binding.setCityViewModel(cityViewModel);
        binding.includeLayout.setCityViewModel(cityViewModel);
        injectDagger();
        setRecyclerView();
        cityViewModel.setDayChart(binding.lineChart);
        cityViewModel.getWeatherData();
        cityViewModel.startRxStream();
        dayRecyclerViewAdapter.notifyDataSetChanged();
        binding.swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void onRefresh() {
        if (cityViewModel.checkIfLastRefreshTimeIsMoreThanHalfHour()) {
            binding.swipeRefreshLayout.setRefreshing(true);
            cityViewModel.refreshData();
        }
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
        dayRecyclerViewAdapter.notifyDataSetChanged();
        binding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void startMyCitiesActivity() {
        getActivity().startActivity(new Intent(getActivity(), MyCitiesViewActivity.class));
        getActivity().finish();
    }

    @Override
    public void turnOffSwipeToRefresh() {
        getActivity().runOnUiThread(() -> {
            singleToast.show(getActivity(), getString(R.string.no_internet_connection), Toast.LENGTH_SHORT);
            binding.swipeRefreshLayout.setRefreshing(false);
        });
    }

    public void injectDagger() {
        App.getApplication().getWeatherComponent().inject(cityViewModel);
    }

    @Override
    public void openDarkSkyWebSite() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Const.KEY_DARKSKY_WWW)));
    }

    @Override
    public void refreshInterval(String info) {
        binding.swipeRefreshLayout.setRefreshing(false);
        singleToast.show(getActivity(), info, Toast.LENGTH_SHORT);
    }

    public void selectCityFromDatabase() {
        Bundle args = getArguments();
        String cityPlaceId = args.getString(Const.KEY_CITY);
        realm.executeTransaction(realm -> city = realm.where(City.class).equalTo("placeId", cityPlaceId).findFirst());
    }
}
