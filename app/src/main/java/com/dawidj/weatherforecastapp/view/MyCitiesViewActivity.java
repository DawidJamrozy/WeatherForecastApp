package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.widget.Toast;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.MyCitiesActivityBinding;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.utils.SingleToast;
import com.dawidj.weatherforecastapp.utils.listeners.MyCitiesViewDataListener;
import com.dawidj.weatherforecastapp.utils.RecyclerHelper;
import com.dawidj.weatherforecastapp.view.adapters.CitiesRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.MyCitiesViewModel;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.dawidj.weatherforecastapp.utils.Const.*;

public class MyCitiesViewActivity extends AppCompatActivity implements MyCitiesViewDataListener {

    private MyCitiesActivityBinding binding;
    private MyCitiesViewModel myCitiesViewModel;
    private CitiesRecyclerViewAdapter citiesRecyclerViewAdapter;
    private boolean doubleBackToExitPressedOnce;

    @Inject
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.my_cities_activity);
        myCitiesViewModel = new MyCitiesViewModel(this);
        binding.setViewModel(myCitiesViewModel);
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(myCitiesViewModel);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(savedInstanceState == null) {
            realm.beginTransaction();
            RealmResults<City> cities = realm.where(City.class).findAllSorted(KEY_SORT, Sort.ASCENDING);
            realm.cancelTransaction();
            myCitiesViewModel.getCityList().addAll(cities);
        }
        setRecycler();
        myCitiesViewModel.checkIfListIsEmpty();
        // TODO: 19.12.2016 Fix drag & drop - position of cities in fragments
    }

    public void setRecycler() {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(myCitiesViewModel.getCityList(), myCitiesViewModel);
        binding.recyclerView.setAdapter(citiesRecyclerViewAdapter);
        ItemTouchHelper.Callback callback = new RecyclerHelper(citiesRecyclerViewAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NEW_CITY) {
            if (resultCode == CITY_INSERTED) {
                int position = data.getIntExtra(POSITION, 0);
                realm.beginTransaction();
                City city = realm.where(City.class).equalTo(KEY_ID, position).findFirst();
                realm.cancelTransaction();
                myCitiesViewModel.getCityList().add(city);
                citiesRecyclerViewAdapter.notifyItemInserted(position);
                myCitiesViewModel.checkIfListIsEmpty();
            } else if (requestCode == ON_BACK_PRESSED) {
                //do nothing
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
           if(myCitiesViewModel.getCityList().isEmpty()) {
               SingleToast.show(this, getString(R.string.add_city), Toast.LENGTH_SHORT);
           } else {
               startActivity(new Intent(this, MainActivity.class));
               finish();
           }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    public void onClickFab() {
        startActivityForResult(new Intent(this, SearchActivity.class), ADD_NEW_CITY);
    }

    @Override
    public void removeCity(int position) {
        citiesRecyclerViewAdapter.notifyItemRemoved(position);
    }
}
