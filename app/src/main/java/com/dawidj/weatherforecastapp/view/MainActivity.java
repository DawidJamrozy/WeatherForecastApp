package com.dawidj.weatherforecastapp.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.view.adapters.ViewPagerAdapter;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpViewPagerAdapter();
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setUpViewPagerAdapter() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new CityViewFragment(), "City");
        viewPagerAdapter.addFragment(new CityViewFragment(), "City2");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
