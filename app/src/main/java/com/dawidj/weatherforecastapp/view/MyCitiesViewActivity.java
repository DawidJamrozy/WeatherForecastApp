package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.widget.Toast;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.MyCitiesActivityBinding;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.utils.RecyclerViewTouchHelper;
import com.dawidj.weatherforecastapp.utils.SingleToast;
import com.dawidj.weatherforecastapp.utils.listeners.MyCitiesViewDataListener;
import com.dawidj.weatherforecastapp.view.adapters.CitiesRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.MyCitiesViewModel;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.Sort;

public class MyCitiesViewActivity extends AppCompatActivity implements MyCitiesViewDataListener {

    private MyCitiesActivityBinding binding;
    private MyCitiesViewModel myCitiesViewModel;
    private CitiesRecyclerViewAdapter citiesRecyclerViewAdapter;
    private SingleToast singleToast = new SingleToast();

    @Inject
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.my_cities_activity);
        myCitiesViewModel = new MyCitiesViewModel(this);
        binding.setViewModel(myCitiesViewModel);
        injectDagger();
        setActionBar();
        realm.executeTransaction(realm ->
                myCitiesViewModel.getCityList().addAll(realm.where(City.class).findAllSorted(Const.KEY_SORT, Sort.ASCENDING)));
        setRecycler();
        myCitiesViewModel.checkIfListIsEmpty();
    }

    public void setRecycler() {
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(myCitiesViewModel.getCityList(), myCitiesViewModel);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(citiesRecyclerViewAdapter);
        ItemTouchHelper.Callback callback = new RecyclerViewTouchHelper(citiesRecyclerViewAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.ADD_NEW_CITY) {
            if (resultCode == Const.CITY_INSERTED) {
                int position = data.getIntExtra(Const.POSITION, 0);
                realm.executeTransaction(realm ->
                    myCitiesViewModel.getCityList().add(realm.where(City.class).equalTo(Const.KEY_ID, position).findFirst()));
                citiesRecyclerViewAdapter.notifyItemInserted(position);
                myCitiesViewModel.checkIfListIsEmpty();
            } else if (resultCode == Const.ON_BACK_PRESSED) {
                //Do nothing
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (myCitiesViewModel.getCityList().isEmpty()) {
                singleToast.show(this, getString(R.string.add_city), Toast.LENGTH_SHORT);
            } else {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(myCitiesViewModel.getCityList().isEmpty()) {
            super.onBackPressed();
        } else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClickFab() {
        startActivityForResult(new Intent(this, SearchActivity.class), Const.ADD_NEW_CITY);
    }

    @Override
    public void removeCity(int position) {
        citiesRecyclerViewAdapter.notifyItemRemoved(position);
    }

    public void setActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void injectDagger() {
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(myCitiesViewModel);
    }

    @Override
    public void toManyClick() {
        singleToast.show(this, getString(R.string.to_many_clicks), Toast.LENGTH_SHORT);
    }
}
