package com.dawidj.weatherforecastapp.utils;

import com.dawidj.weatherforecastapp.models.darksky.HourlyData;

import org.parceler.Parcels;

/**
 * Created by djamrozy on 16.12.2016.
 */

public class HourlyDataParcelConverter extends RealmListParcelConverter<HourlyData> {

    @Override
    public void itemToParcel(HourlyData input, android.os.Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);
    }

    @Override
    public HourlyData itemFromParcel(android.os.Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(HourlyData.class.getClassLoader()));
    }
}

