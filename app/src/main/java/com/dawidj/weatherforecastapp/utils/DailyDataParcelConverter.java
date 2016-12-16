package com.dawidj.weatherforecastapp.utils;

import android.os.Parcel;

import com.dawidj.weatherforecastapp.models.dbtest.DailyData;

import org.parceler.Parcels;

/**
 * Created by djamrozy on 16.12.2016.
 */

public class DailyDataParcelConverter extends RealmListParcelConverter<DailyData> {

    @Override
    public void itemToParcel(DailyData input, Parcel parcel) {
        parcel.writeParcelable(Parcels.wrap(input), 0);

    }


    @Override
    public DailyData itemFromParcel(Parcel parcel) {
        return Parcels.unwrap(parcel.readParcelable(DailyData.class.getClassLoader()));

    }


}
