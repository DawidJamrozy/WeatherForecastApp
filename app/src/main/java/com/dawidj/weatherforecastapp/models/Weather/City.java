package com.dawidj.weatherforecastapp.models.Weather;

import android.databinding.BaseObservable;

import com.dawidj.weatherforecastapp.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by Dawidj on 24.10.2016.
 */
@Entity
public class City extends BaseObservable {

    @Id
    private Long id;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("currently")
    @Expose
    @ToOne(joinProperty = "name")
    private Currently currently;
    @SerializedName("hourly")
    @Expose
    @ToOne(joinProperty = "name")
    private Hourly hourly;
    @SerializedName("daily")
    @Expose
    @ToOne(joinProperty = "name")
    private Daily daily;

    /**
     * @return The name
     */
    @Keep
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    @Keep
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The latitude
     */
    @Keep
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude The latitude
     */
    @Keep
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The longitude
     */
    @Keep
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude The longitude
     */
    @Keep
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The timezone
     */
    @Keep
    public String getTimezone() {
        return timezone;
    }

    /**
     * @param timezone The timezone
     */
    @Keep
    public void setTimezone(String timezone) {
        this.timezone = timezone;
        notifyPropertyChanged(BR._all);
    }


    /**
     * @return The currently
     */
    @Keep
    public Currently getCurrently() {
        return currently;
    }

    /**
     * @param currently The currently
     */
    @Keep
    public void setCurrently(Currently currently) {
        this.currently = currently;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The hourly
     */
    @Keep
    public Hourly getHourly() {
        return hourly;
    }

    /*
     * @param hourly The hourly
     */
    @Keep
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The daily
     */
    @Keep
    public Daily getDaily() {
        return daily;
    }

    /**
     * @param daily The daily
     */
    @Keep
    public void setDaily(Daily daily) {
        this.daily = daily;
        notifyPropertyChanged(BR._all);
    }

}
