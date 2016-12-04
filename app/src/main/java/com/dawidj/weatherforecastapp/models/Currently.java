package com.dawidj.weatherforecastapp.models;

import android.databinding.BaseObservable;

import com.dawidj.weatherforecastapp.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dawidj on 24.10.2016.
 */

public class Currently extends BaseObservable {

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("precipIntensity")
    @Expose
    private Double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    private Double precipProbability;
    @SerializedName("temperature")
    @Expose
    private Double temperature;
    @SerializedName("apparentTemperature")
    @Expose
    private Double apparentTemperature;
    @SerializedName("dewPoint")
    @Expose
    private Double dewPoint;
    @SerializedName("humidity")
    @Expose
    private Double humidity;
    @SerializedName("windSpeed")
    @Expose
    private Double windSpeed;
    @SerializedName("windBearing")
    @Expose
    private Double windBearing;
    @SerializedName("cloudCover")
    @Expose
    private Double cloudCover;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("ozone")
    @Expose
    private Double ozone;

    /**
     *
     * @return
     * The time
     */
    public Integer getTime() {
        return time;
    }

    /**
     *
     * @param time
     * The time
     */
    public void setTime(Integer time) {
        this.time = time;
        notifyPropertyChanged(BR._all);
    }

    /**
     *
     * @return
     * The summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     * The summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
        notifyPropertyChanged(BR._all);
    }

    /**
     *
     * @return
     * The precipIntensity
     */
    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    /**
     *
     * @param precipIntensity
     * The precipIntensity
     */
    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The precipProbability
     */
    public Double getPrecipProbability() {
        return precipProbability;
    }

    /**
     *
     * @param precipProbability
     * The precipProbability
     */
    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The temperature
     */
    public Double getTemperature() {
        return temperature;
    }

    /**
     *
     * @param temperature
     * The temperature
     */
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The apparentTemperature
     */
    public Double getApparentTemperature() {
        return apparentTemperature;

    }

    /**
     *
     * @param apparentTemperature
     * The apparentTemperature
     */
    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The dewPoint
     */
    public Double getDewPoint() {
        return dewPoint;
    }

    /**
     *
     * @param dewPoint
     * The dewPoint
     */
    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The humidity
     */
    public Double getHumidity() {
        return humidity*100;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The windSpeed
     */
    public Double getWindSpeed() {
        return windSpeed;
    }

    /**
     *
     * @param windSpeed
     * The windSpeed
     */
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The windBearing
     */
    public Double getWindBearing() {
        return windBearing;
    }

    /**
     *
     * @param windBearing
     * The windBearing
     */
    public void setWindBearing(Double windBearing) {
        this.windBearing = windBearing;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The cloudCover
     */
    public Double getCloudCover() {
        return cloudCover;
    }

    /**
     *
     * @param cloudCover
     * The cloudCover
     */
    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
        notifyPropertyChanged(BR._all);

    }

    /**
     *
     * @return
     * The pressure
     */
    public Double getPressure() {
        return pressure;

    }

    /**
     *
     * @param pressure
     * The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
        notifyPropertyChanged(BR._all);
    }

    /**
     *
     * @return
     * The ozone
     */
    public Double getOzone() {
        return ozone;
    }

    /**
     *
     * @param ozone
     * The ozone
     */
    public void setOzone(Double ozone) {
        this.ozone = ozone;
        notifyPropertyChanged(BR._all);

    }
}
