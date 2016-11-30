package com.dawidj.weatherforecastapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dawidj on 24.10.2016.
 */

public class City {


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
    @SerializedName("offset")
    @Expose
    private Double offset;
    @SerializedName("currently")
    @Expose
    private Currently currently;
    @SerializedName("hourly")
    @Expose
    private Hourly hourly;
    @SerializedName("daily")
    @Expose
    private Daily daily;
    @SerializedName("flags")
    @Expose
    private Flags flags;


    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The timezone
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     *
     * @param timezone
     * The timezone
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     *
     * @return
     * The offset
     */
    public Double getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(Double offset) {
        this.offset = offset;
    }

    /**
     *
     * @return
     * The currently
     */
    public Currently getCurrently() {
        return currently;
    }

    /**
     *
     * @param currently
     * The currently
     */
    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    /**
     *
     * @return
     * The hourly
     */
    public Hourly getHourly() {
        return hourly;
    }

    /**
     *
     * @param hourly
     * The hourly
     */
    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    /**
     *
     * @return
     * The daily
     */
    public Daily getDaily() {
        return daily;
    }

    /**
     *
     * @param daily
     * The daily
     */
    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    /**
     *
     * @return
     * The flags
     */
    public Flags getFlags() {
        return flags;
    }

    /**
     *
     * @param flags
     * The flags
     */
    public void setFlags(Flags flags) {
        this.flags = flags;
    }
}
