package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.MainActivityBinding;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.utils.Const;
import com.dawidj.weatherforecastapp.view.adapters.ViewPagerAdapter;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;
    private boolean doubleBackToExitPressedOnce;

    @Inject
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getApplication().getWeatherComponent().inject(this);
        checkDatabaseSize();
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        realm.executeTransaction(realm ->
            setUpViewPagerAdapter(realm.where(City.class).findAllSorted(Const.KEY_SORT, Sort.ASCENDING)));
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
        args.putString(Const.KEY_CITY, city.getPlaceId());
        //args.putParcelable(Const.KEY_CITY, Parcels.wrap(city));
        fragment.setArguments(args);
        return fragment;
    }

    public void checkDatabaseSize() {
        if (realm.where(City.class).findAll().size() == 0) {
            startActivity(new Intent(this, MyCitiesViewActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.double_back, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }
}
