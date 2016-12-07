package com.dawidj.weatherforecastapp.viewModel;

import android.databinding.ObservableField;

import com.dawidj.weatherforecastapp.models.Location;
import com.dawidj.weatherforecastapp.utils.busevent.LocationEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Dawidj on 04.12.2016.
 */

public class LocationViewModel {

    private List<Location> locationList = new ArrayList<>();
    private String name = "Location List";
    private ObservableField<String> cityName = new ObservableField<>();

    public ObservableField<String> getCityName() {return cityName;}

    public String getName() {
        return name;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Inject
    EventBus eventBus;

    public LocationViewModel() {
    }

    public void addItem() {
        Location location = new Location();
        location.setName(getCityName().get());
        locationList.add(location);
        eventBus.post(new LocationEvent());
        //TODO clear edit text after adding new item
    }

}
