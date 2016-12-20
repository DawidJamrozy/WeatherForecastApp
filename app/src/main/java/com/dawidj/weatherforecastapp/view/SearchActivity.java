package com.dawidj.weatherforecastapp.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.SearchActivityBinding;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;
import com.dawidj.weatherforecastapp.utils.ItemClickSupport;
import com.dawidj.weatherforecastapp.utils.listeners.SearchViewDataListener;
import com.dawidj.weatherforecastapp.view.adapters.SearchRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.SearchViewModel;

import java.util.List;

import static com.dawidj.weatherforecastapp.utils.Const.CITY_INSERTED;
import static com.dawidj.weatherforecastapp.utils.Const.ON_BACK_PRESSED;
import static com.dawidj.weatherforecastapp.utils.Const.POSITION;

public class SearchActivity extends AppCompatActivity implements SearchViewDataListener {

    private SearchRecyclerViewAdapter searchRecyclerViewAdapter;
    private SearchViewModel searchViewModel;
    private SearchActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.search_activity);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        searchViewModel = new SearchViewModel(this);
        binding.setSearchViewModel(searchViewModel);
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(searchViewModel);
        setRecycler();
        searchViewModel.rxQueryBuilder();
    }

    public void setRecycler() {
        searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(searchViewModel.getCityLatLngList());
        binding.autocompleteRecyclerView.setHasFixedSize(true);
        binding.autocompleteRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.autocompleteRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.autocompleteRecyclerView.setAdapter(searchRecyclerViewAdapter);
        ItemClickSupport.addTo(binding.autocompleteRecyclerView)
                .setOnItemClickListener((RecyclerView r, int position, View v) -> searchViewModel.addCity(position));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            setResult(ON_BACK_PRESSED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchViewModel.onDestroy();
    }

    @Override
    public void onBackPressed() {
        setResult(ON_BACK_PRESSED);
        super.onBackPressed();
    }

    @Override
    public void newCityAdded(int position) {
        Intent intent = new Intent();
        intent.putExtra(POSITION, position);
        setResult(CITY_INSERTED, intent);
        finish();
    }

    @Override
    public void notifyAdapter(List<CityLatLng> list) {
        searchRecyclerViewAdapter.setList(list);
    }
}
