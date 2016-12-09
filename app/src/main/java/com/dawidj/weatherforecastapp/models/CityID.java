package com.dawidj.weatherforecastapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by djamrozy on 09.12.2016.
 */

public class CityID {

    @SerializedName("placeID")
    @Expose
    private String placeID;

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }
}
