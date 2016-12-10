package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.view.adapters.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    private String[] cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Timber.i("onCreate(): ");
        Bundle extras = getIntent().getExtras();
        cities =  extras.getStringArray("locationList");
        setUpViewPagerAdapter(cities);
        tabLayout.setupWithViewPager(viewPager);

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

    public void setUpViewPagerAdapter(String[] cityTable) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        for (String cityName : cityTable) {
            viewPagerAdapter.addFragment(cityViewFragment(cityName), cityName);
        }
        viewPager.setAdapter(viewPagerAdapter);
    }

    public CityFragment cityViewFragment(String city) {
        CityFragment fragment = new CityFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("cityName", city);
        fragment.setArguments(args);
        return fragment;
    }
}
