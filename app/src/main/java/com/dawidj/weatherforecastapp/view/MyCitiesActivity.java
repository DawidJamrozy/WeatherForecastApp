package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.MyCitiesActivityBinding;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.utils.RecyclerHelper;
import com.dawidj.weatherforecastapp.view.adapters.CitiesRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.view.adapters.NotifyAdapter;
import com.dawidj.weatherforecastapp.viewModel.MyCitiesViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

import static com.dawidj.weatherforecastapp.utils.Const.ADD_NEW_CITY;
import static com.dawidj.weatherforecastapp.utils.Const.CITY_INSERTED;
import static com.dawidj.weatherforecastapp.utils.Const.ON_BACK_PRESSED;
import static com.dawidj.weatherforecastapp.utils.Const.POSITION;

public class MyCitiesActivity extends AppCompatActivity implements NotifyAdapter {

    private MyCitiesActivityBinding binding;
    private MyCitiesViewModel myCitiesViewModel;
    private CitiesRecyclerViewAdapter citiesRecyclerViewAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.startSearchActivity)
    Button button;

    @Inject
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.my_cities_activity);
        myCitiesViewModel = new MyCitiesViewModel();
        binding.setViewModel(myCitiesViewModel);
        ButterKnife.bind(this);
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(myCitiesViewModel);
        myCitiesViewModel.setNotifyAdapter(this);
        if(savedInstanceState == null) {
            realm.beginTransaction();
            RealmResults<City> cities = realm.where(City.class).findAll();
            realm.cancelTransaction();
            myCitiesViewModel.getCityList().addAll(cities);
        }
        setSupportActionBar(toolbar);
        setRecycler();
        // TODO: 19.12.2016 Fix drag & drop - position of cities in fragments
    }

    public void setRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(myCitiesViewModel.getCityList(), myCitiesViewModel);
        recyclerView.setAdapter(citiesRecyclerViewAdapter);
        ItemTouchHelper.Callback callback = new RecyclerHelper(citiesRecyclerViewAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @OnClick(R.id.startSearchActivity)
    public void startMainActivity(View view) {
        startActivity(new Intent(this, MainActivity.class));
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
                startActivityForResult(i, ADD_NEW_CITY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NEW_CITY) {
            if (resultCode == CITY_INSERTED) {
                int position = data.getIntExtra(POSITION, 0);
                realm.beginTransaction();
                City city = realm.where(City.class).equalTo("id", position).findFirst();
                realm.cancelTransaction();
                myCitiesViewModel.getCityList().add(city);
                citiesRecyclerViewAdapter.notifyItemInserted(position);
            } else if (requestCode == ON_BACK_PRESSED) {
                //do nothing
            }
        }
    }

    @Override
    public void notifyAdapter(int position) {
        citiesRecyclerViewAdapter.notifyItemRemoved(position);
    }
}
