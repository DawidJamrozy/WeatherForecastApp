package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.LoctionActivityBinding;
import com.dawidj.weatherforecastapp.utils.busevent.LocationEvent;
import com.dawidj.weatherforecastapp.view.adapters.LocationRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.LocationViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LocationActivity extends AppCompatActivity {

    @BindView(R.id.locationList)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.start)
    Button button;

    private LocationRecyclerViewAdapter locationRecyclerViewAdapter;
    private LocationViewModel locationViewModel;
    private LoctionActivityBinding binding;
    @Inject
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.loction_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        locationViewModel = new LocationViewModel();
        binding.setLocationViewModel(locationViewModel);
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(locationViewModel);

        setRecyclerView();
    }

    public void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        locationRecyclerViewAdapter = new LocationRecyclerViewAdapter(this, locationViewModel.getLocationList());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(locationRecyclerViewAdapter);
    }

    @OnClick(R.id.start)
    public void loadCities() {
        String[] strings = new String[locationViewModel.getLocationList().size()];
        for (int i = 0; i < locationViewModel.getLocationList().size(); i++) {
            strings[i] = locationViewModel.getLocationList().get(i).getName();
            Timber.i("loadCities(): " + strings[i].toString());
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("locationList", strings);
        Timber.i("loadCities(): " + strings[0]);
        startActivity(intent);
        finish();
    }


    @OnClick(R.id.add)
    public void addLocation(View view) {
        locationViewModel.addItem();
    }

    @Subscribe
    public void notifyAdapter(LocationEvent event) {
        Timber.i("notifyAdapter(): ");
        locationRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        locationViewModel.onDestroy();
    }
}
