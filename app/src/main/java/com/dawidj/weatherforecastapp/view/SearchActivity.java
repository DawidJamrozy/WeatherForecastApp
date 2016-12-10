package com.dawidj.weatherforecastapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.SearchActivityBinding;
import com.dawidj.weatherforecastapp.utils.ItemClickSupport;
import com.dawidj.weatherforecastapp.utils.busevent.AddLocation;
import com.dawidj.weatherforecastapp.utils.busevent.ClearLocation;
import com.dawidj.weatherforecastapp.view.adapters.SearchRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.SearchViewModel;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class SearchActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.locationList)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.start)
    Button button;

    private SearchRecyclerViewAdapter searchRecyclerViewAdapter;
    private SearchViewModel searchViewModel;
    private SearchActivityBinding binding;

    @Inject
    EventBus eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.search_activity);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        searchViewModel = new SearchViewModel();
        binding.setSearchViewModel(searchViewModel);
        App.getApplication().getWeatherComponent().inject(this);
        App.getApplication().getWeatherComponent().inject(searchViewModel);
        setRecyclerView();
        searchViewModel.rxQueryBuilder();
    }

    public void setRecyclerView() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(this, searchViewModel.getCityLatLngList());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(searchRecyclerViewAdapter);
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                searchViewModel.addCity(position);
            }
        });
    }

    @OnClick(R.id.start)
    public void loadCities() {
    /*    String[] strings = new String[searchViewModel.getLocationList().size()];
        for (int i = 0; i < searchViewModel.getLocationList().size(); i++) {
            strings[i] = searchViewModel.getLocationList().get(i).getName();
            Timber.i("loadCities(): " + strings[i].toString());
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("locationList", strings);
        Timber.i("loadCities(): " + strings[0]);
        startActivity(intent);
        finish();*/
    }


    @OnClick(R.id.add)
    public void addLocation(View view) {
        /*searchViewModel.addItem();*/
    }

    @Subscribe
    public void notifyAdapterToAdd(AddLocation event) {
        Timber.i("notifyAdapterToAdd(): ");
        searchRecyclerViewAdapter.notifyItemInserted(searchViewModel.getCityLatLngList().size() - 1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void notifyAdapterToClear(ClearLocation event) {
        Timber.i("notifyAdapterToClear(): ");
        searchRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        searchViewModel.onDestroy();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e(LOG_TAG, "Google Places API connection failed with error code: "
                + connectionResult.getErrorCode());

        Toast.makeText(this,
                "Google Places API connection failed with error code:" +
                        connectionResult.getErrorCode(),
                Toast.LENGTH_LONG).show();
    }
}
