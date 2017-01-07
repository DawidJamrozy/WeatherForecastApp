package com.dawidj.weatherforecastapp.view;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.SearchActivityBinding;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;
import com.dawidj.weatherforecastapp.utils.ItemClickSupport;
import com.dawidj.weatherforecastapp.utils.SingleToast;
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
        setActionBar();
        searchViewModel = new SearchViewModel(this, this);
        binding.setSearchViewModel(searchViewModel);
        injectDagger();
        setRecycler();
        searchViewModel.startRxStream();
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
        if (item.getItemId() == android.R.id.home) {
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

    @Override
    public void loseFocus() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(binding.cityEditText.getWindowToken(), 0);
    }

    public void setActionBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void injectDagger() {
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(searchViewModel);
    }

    @Override
    public void showToast(String info) {
        SingleToast singleToast = new SingleToast();
        this.runOnUiThread(() -> singleToast.show(this, info, Toast.LENGTH_SHORT));
    }

    public void checkPermission(View view) {
        String[] Permissions = {Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_NETWORK_STATE};
        if (hasPermissions(this, Permissions)) {
            searchViewModel.locateMe();
        } else {
            ActivityCompat.requestPermissions(this, Permissions, 1);
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    searchViewModel.locateMe();
                }
        }
    }
}
