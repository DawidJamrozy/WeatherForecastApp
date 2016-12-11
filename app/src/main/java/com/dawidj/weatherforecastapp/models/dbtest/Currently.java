package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Dawidj on 24.10.2016.
 */
@Entity
public class Currently extends BaseObservable {
    @Id
    private Long id;

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
    @Generated(hash = 1624391316)
    public Currently(Long id, Integer time, String summary, String icon,
            Double precipIntensity, Double precipProbability, Double temperature,
            Double apparentTemperature, Double dewPoint, Double humidity,
            Double windSpeed, Double windBearing, Double cloudCover,
            Double pressure, Double ozone) {
        this.id = id;
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windBearing = windBearing;
        this.cloudCover = cloudCover;
        this.pressure = pressure;
        this.ozone = ozone;
    }
    @Generated(hash = 814712062)
    public Currently() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getTime() {
        return this.time;
    }
    public void setTime(Integer time) {
        this.time = time;
    }
    public String getSummary() {
        return this.summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Double getPrecipIntensity() {
        return this.precipIntensity;
    }
    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }
    public Double getPrecipProbability() {
        return this.precipProbability;
    }
    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }
    public Double getTemperature() {
        return this.temperature;
    }
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    public Double getApparentTemperature() {
        return this.apparentTemperature;
    }
    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }
    public Double getDewPoint() {
        return this.dewPoint;
    }
    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }
    public Double getHumidity() {
        return this.humidity;
    }
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }
    public Double getWindSpeed() {
        return this.windSpeed;
    }
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public Double getWindBearing() {
        return this.windBearing;
    }
    public void setWindBearing(Double windBearing) {
        this.windBearing = windBearing;
    }
    public Double getCloudCover() {
        return this.cloudCover;
    }
    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }
    public Double getPressure() {
        return this.pressure;
    }
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }
    public Double getOzone() {
        return this.ozone;
    }
    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

}
