package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.models.dbtest.City;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.view.adapters.ViewPagerAdapter;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Inject
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplication().getWeatherComponent().inject(this);
        if(realm.where(City.class).findAll().size() == 0) {
            startActivity(new Intent(this, MyCitiesActivity.class));
            finish();
        } else {
            setContentView(R.layout.activity_main);
            ButterKnife.bind(this);
            realm.beginTransaction();
            RealmResults<City> cities = realm.where(City.class).findAll();
            setUpViewPagerAdapter(cities);
            realm.cancelTransaction();
        }
    }

    public void setUpViewPagerAdapter(List<City> cities) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (City city : cities) {
            viewPagerAdapter.addFragment(cityViewFragment(city), city.getName());
        }
        viewPager.setAdapter(viewPagerAdapter);
    }

    public CityFragment cityViewFragment(City city) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putParcelable(Const.KEY_CITY, Parcels.wrap(city));
        fragment.setArguments(args);
        return fragment;
    }
}
