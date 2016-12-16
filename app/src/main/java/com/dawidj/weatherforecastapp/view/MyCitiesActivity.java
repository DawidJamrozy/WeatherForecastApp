package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.MyCitiesActivityBinding;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.view.adapters.CitiesRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.MyCitiesViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MyCitiesActivity extends AppCompatActivity {

    private MyCitiesActivityBinding binding;
    private MyCitiesViewModel myCitiesViewModel;
    private CitiesRecyclerViewAdapter citiesRecyclerViewAdapter;
    private Realm realm;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.startSearchActivity)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.my_cities_activity);
        myCitiesViewModel = new MyCitiesViewModel();
        binding.setViewModel(myCitiesViewModel);
        ButterKnife.bind(this);
        Realm.init(this);
        realm = Realm.getDefaultInstance();
        RealmResults<City> cities = realm.where(City.class).findAll();
        myCitiesViewModel.getCityList().addAll(cities);
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(myCitiesViewModel);
        setSupportActionBar(toolbar);
        setRecycler();
    }

    public void setRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
       // myCitiesViewModel.getCityList().addAll(daoSession.getCityDao().loadAll());
        citiesRecyclerViewAdapter = new CitiesRecyclerViewAdapter(this, myCitiesViewModel.getCityList());
        recyclerView.setAdapter(citiesRecyclerViewAdapter);
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
                startActivityForResult(i, Const.ADD_NEW_CITY);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Const.ADD_NEW_CITY) {
            if (resultCode == Const.CITY_INSERTED) {
                //int id = data.getIntExtra(Const.POSITION, 0);
                RealmResults<City> cities = realm.where(City.class).findAll();
               // List<City> city = daoSession.getCityDao().queryBuilder()
                       // .where(CityDao.Properties.Id.eq(id)).limit(1).list();
                myCitiesViewModel.getCityList().addAll(cities);

                citiesRecyclerViewAdapter.notifyDataSetChanged();
            } else if (requestCode == Const.ON_BACK_PRESSED) {
                //do nothing
            }
        }
    }
}
