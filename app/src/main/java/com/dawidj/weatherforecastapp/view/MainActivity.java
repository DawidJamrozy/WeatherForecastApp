package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.MainActivityBinding;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.view.adapters.ViewPagerAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

import static com.dawidj.weatherforecastapp.utils.Const.KEY_SORT;

public class MainActivity extends AppCompatActivity {

    MainActivityBinding binding;

    @Inject
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplication().getWeatherComponent().inject(this);
        checkDatabaseSize();
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        realm.executeTransaction(realm -> {
            RealmResults<City> cities = realm.where(City.class).findAllSorted(KEY_SORT, Sort.ASCENDING);
            setUpViewPagerAdapter(cities);
        });
    }

    public void setUpViewPagerAdapter(List<City> cities) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (City city : cities) {
            viewPagerAdapter.addFragment(cityViewFragment(city), city.getName());
        }
        binding.viewPager.setAdapter(viewPagerAdapter);
    }

    public CityActivity cityViewFragment(City city) {
        CityActivity fragment = new CityActivity();
        Bundle args = new Bundle();
        args.putParcelable(Const.KEY_CITY, Parcels.wrap(city));
        fragment.setArguments(args);
        return fragment;
    }

    public void checkDatabaseSize() {
        if (realm.where(City.class).findAll().size() == 0) {
            startActivity(new Intent(this, MyCitiesViewActivity.class));
            finish();
        }
    }
}
