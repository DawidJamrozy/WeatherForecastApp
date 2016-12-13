package com.dawidj.weatherforecastapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.SearchActivityBinding;
import com.dawidj.weatherforecastapp.utils.ItemClickSupport;
import com.dawidj.weatherforecastapp.utils.busevent.AddLocation;
import com.dawidj.weatherforecastapp.utils.busevent.ClearLocation;
import com.dawidj.weatherforecastapp.utils.busevent.NotifyRecyclerAdapterEvent;
import com.dawidj.weatherforecastapp.view.adapters.SearchRecyclerViewAdapter;
import com.dawidj.weatherforecastapp.viewModel.SearchViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

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

    @Subscribe
    public void notifyAdapterToAdd(AddLocation event) {
        Timber.i("notifyAdapterToAdd(): ");
        searchRecyclerViewAdapter.setList(event.getCityLatLngs());
//        searchRecyclerViewAdapter.notifyItemInserted(searchViewModel.getCityLatLngList().size() - 1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void notifyAdapterToClear(ClearLocation event) {
        Timber.i("notifyAdapterToClear(): ");
        searchRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Subscribe
    public void finishActivity(NotifyRecyclerAdapterEvent event) {
        Timber.i("finishActivity(): ");
        finish();
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
}
