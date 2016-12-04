package com.dawidj.weatherforecastapp.models;

import android.databinding.BaseObservable;

import com.dawidj.weatherforecastapp.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import static com.android.databinding.library.baseAdapters.BR._all;

/**
 * Created by Dawidj on 24.10.2016.
 */

public class Hourly extends BaseObservable {

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose
    private List<HourlyData> data = new ArrayList<HourlyData>();

    /**
     * @return The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The data
     */
    public List<HourlyData> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<HourlyData> data) {
        this.data = data;
        notifyPropertyChanged(BR._all);
    }
}
