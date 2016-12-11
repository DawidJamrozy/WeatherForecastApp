package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Dawidj on 25.10.2016.
 */
@Entity
public class DailyData extends BaseObservable {

    @Id
    private Long id;

    private Long dailyID;

    @SerializedName("time")
    @Expose
    private Integer time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("sunriseTime")
    @Expose
    private Double sunriseTime;
    @SerializedName("sunsetTime")
    @Expose
    private Double sunsetTime;
    @SerializedName("moonPhase")
    @Expose
    private Double moonPhase;
    @SerializedName("precipIntensity")
    @Expose
    private Double precipIntensity;
    @SerializedName("precipIntensityMax")
    @Expose
    private Double precipIntensityMax;
    @SerializedName("precipIntensityMaxTime")
    @Expose
    private Double precipIntensityMaxTime;
    @SerializedName("precipProbability")
    @Expose
    private Double precipProbability;
    @SerializedName("precipType")
    @Expose
    private String precipType;
    @SerializedName("temperatureMin")
    @Expose
    private Double temperatureMin;
    @SerializedName("temperatureMinTime")
    @Expose
    private Double temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    private Double temperatureMax;
    @SerializedName("temperatureMaxTime")
    @Expose
    private Double temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    @Expose
    private Double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    @Expose
    private Double apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    @Expose
    private Double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    @Expose
    private Double apparentTemperatureMaxTime;
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
    @Generated(hash = 1936653682)
    public DailyData(Long id, Long dailyID, Integer time, String summary,
            String icon, Double sunriseTime, Double sunsetTime, Double moonPhase,
            Double precipIntensity, Double precipIntensityMax,
            Double precipIntensityMaxTime, Double precipProbability,
            String precipType, Double temperatureMin, Double temperatureMinTime,
            Double temperatureMax, Double temperatureMaxTime,
            Double apparentTemperatureMin, Double apparentTemperatureMinTime,
            Double apparentTemperatureMax, Double apparentTemperatureMaxTime,
            Double dewPoint, Double humidity, Double windSpeed, Double windBearing,
            Double cloudCover, Double pressure, Double ozone) {
        this.id = id;
        this.dailyID = dailyID;
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.moonPhase = moonPhase;
        this.precipIntensity = precipIntensity;
        this.precipIntensityMax = precipIntensityMax;
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperatureMin = temperatureMin;
        this.temperatureMinTime = temperatureMinTime;
        this.temperatureMax = temperatureMax;
        this.temperatureMaxTime = temperatureMaxTime;
        this.apparentTemperatureMin = apparentTemperatureMin;
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        this.apparentTemperatureMax = apparentTemperatureMax;
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windBearing = windBearing;
        this.cloudCover = cloudCover;
        this.pressure = pressure;
        this.ozone = ozone;
    }
    @Generated(hash = 556979270)
    public DailyData() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getDailyID() {
        return this.dailyID;
    }
    public void setDailyID(Long dailyID) {
        this.dailyID = dailyID;
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
    public Double getSunriseTime() {
        return this.sunriseTime;
    }
    public void setSunriseTime(Double sunriseTime) {
        this.sunriseTime = sunriseTime;
    }
    public Double getSunsetTime() {
        return this.sunsetTime;
    }
    public void setSunsetTime(Double sunsetTime) {
        this.sunsetTime = sunsetTime;
    }
    public Double getMoonPhase() {
        return this.moonPhase;
    }
    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }
    public Double getPrecipIntensity() {
        return this.precipIntensity;
    }
    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }
    public Double getPrecipIntensityMax() {
        return this.precipIntensityMax;
    }
    public void setPrecipIntensityMax(Double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }
    public Double getPrecipIntensityMaxTime() {
        return this.precipIntensityMaxTime;
    }
    public void setPrecipIntensityMaxTime(Double precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }
    public Double getPrecipProbability() {
        return this.precipProbability;
    }
    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }
    public String getPrecipType() {
        return this.precipType;
    }
    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }
    public Double getTemperatureMin() {
        return this.temperatureMin;
    }
    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }
    public Double getTemperatureMinTime() {
        return this.temperatureMinTime;
    }
    public void setTemperatureMinTime(Double temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
    }
    public Double getTemperatureMax() {
        return this.temperatureMax;
    }
    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }
    public Double getTemperatureMaxTime() {
        return this.temperatureMaxTime;
    }
    public void setTemperatureMaxTime(Double temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
    }
    public Double getApparentTemperatureMin() {
        return this.apparentTemperatureMin;
    }
    public void setApparentTemperatureMin(Double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }
    public Double getApparentTemperatureMinTime() {
        return this.apparentTemperatureMinTime;
    }
    public void setApparentTemperatureMinTime(Double apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
    }
    public Double getApparentTemperatureMax() {
        return this.apparentTemperatureMax;
    }
    public void setApparentTemperatureMax(Double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }
    public Double getApparentTemperatureMaxTime() {
        return this.apparentTemperatureMaxTime;
    }
    public void setApparentTemperatureMaxTime(Double apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
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