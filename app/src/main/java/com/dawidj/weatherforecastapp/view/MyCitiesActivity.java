package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.MyCitiesActivityBinding;
import com.dawidj.weatherforecastapp.models.dbtest.DaoSession;
import com.dawidj.weatherforecastapp.view.adapters.CitiesRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.MyCitiesViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyCitiesActivity extends AppCompatActivity {

    private MyCitiesActivityBinding binding;
    private MyCitiesViewModel myCitiesViewModel;
    private CitiesRecyclerViewAdapter citiesRecyclerViewAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.my_cities_activity);
        myCitiesViewModel = new MyCitiesViewModel();
        binding.setViewModel(myCitiesViewModel);
        ButterKnife.bind(this);
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(myCitiesViewModel);
        setSupportActionBar(toolbar);
        setRecycler();
    }

    public void setRecycler() {
        recyclerView.setHasFixedSize(true);
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(this, myCitiesViewModel.getCityList());
        recyclerView.setAdapter(citiesRecyclerViewAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                Intent i = new Intent(this, SearchActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
