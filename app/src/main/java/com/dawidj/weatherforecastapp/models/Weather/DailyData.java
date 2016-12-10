package com.dawidj.weatherforecastapp.models.Weather;

import android.databinding.BaseObservable;

import com.dawidj.weatherforecastapp.BR;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Dawidj on 25.10.2016.
 */
@Entity
public class DailyData extends BaseObservable {

    @Id
    private Long id;

    private String dailyDataID;
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

    @Generated(hash = 852559969)
    public DailyData(Long id, String dailyDataID, Integer time, String summary,
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
        this.dailyDataID = dailyDataID;
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

    @Keep
    public String getDailyDataID() {
        return dailyDataID;
    }

    @Keep
    public void setDailyDataID(String dailyDataID) {
        this.dailyDataID = dailyDataID;
    }

    /**
     * @return The time
     */
    @Keep
    public Integer getTime() {
        return time;
    }

    /**
     * @param time The time
     */
    @Keep
    public void setTime(Integer time) {
        this.time = time;
    }

    /**
     * @return The summary
     */
    @Keep
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary The summary
     */
    @Keep
    public void setSummary(String summary) {
        this.summary = summary;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The icon
     */
    @Keep
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon
     */
    @Keep
    public void setIcon(String icon) {
        this.icon = icon;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The sunriseTime
     */
    @Keep
    public Double getSunriseTime() {
        return sunriseTime;
    }

    /**
     * @param sunriseTime The sunriseTime
     */
    @Keep
    public void setSunriseTime(Double sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    /**
     * @return The sunsetTime
     */
    @Keep
    public Double getSunsetTime() {
        return sunsetTime;
    }

    /**
     * @param sunsetTime The sunsetTime
     */
    @Keep
    public void setSunsetTime(Double sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    /**
     * @return The moonPhase
     */
    @Keep
    public Double getMoonPhase() {
        return moonPhase;
    }

    /**
     * @param moonPhase The moonPhase
     */
    @Keep
    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
    }

    /**
     * @return The precipIntensity
     */
    @Keep
    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    /**
     * @param precipIntensity The precipIntensity
     */
    @Keep
    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    /**
     * @return The precipIntensityMax
     */
    @Keep
    public Double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    /**
     * @param precipIntensityMax The precipIntensityMax
     */
    @Keep
    public void setPrecipIntensityMax(Double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    /**
     * @return The precipIntensityMaxTime
     */
    @Keep
    public Double getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    /**
     * @param precipIntensityMaxTime The precipIntensityMaxTime
     */
    @Keep
    public void setPrecipIntensityMaxTime(Double precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    /**
     * @return The precipProbability
     */
    @Keep
    public Double getPrecipProbability() {
        return precipProbability;
    }

    /**
     * @param precipProbability The precipProbability
     */
    @Keep
    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
    }

    /**
     * @return The precipType
     */
    @Keep
    public String getPrecipType() {
        return precipType;
    }

    /**
     * @param precipType The precipType
     */
    @Keep
    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    /**
     * @return The temperatureMin
     */
    @Keep
    public Double getTemperatureMin() {
        return temperatureMin;
    }

    /**
     * @param temperatureMin The temperatureMin
     */
    @Keep
    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    /**
     * @return The temperatureMinTime
     */
    @Keep
    public Double getTemperatureMinTime() {
        return temperatureMinTime;
    }

    /**
     * @param temperatureMinTime The temperatureMinTime
     */
    @Keep
    public void setTemperatureMinTime(Double temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
    }

    /**
     * @return The temperatureMax
     */
    @Keep
    public Double getTemperatureMax() {
        return temperatureMax;
    }

    /**
     * @param temperatureMax The temperatureMax
     */
    @Keep
    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The temperatureMaxTime
     */
    @Keep
    public Double getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    /**
     * @param temperatureMaxTime The temperatureMaxTime
     */
    @Keep
    public void setTemperatureMaxTime(Double temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
    }

    /**
     * @return The apparentTemperatureMin
     */
    @Keep
    public Double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    /**
     * @param apparentTemperatureMin The apparentTemperatureMin
     */
    @Keep
    public void setApparentTemperatureMin(Double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    /**
     * @return The apparentTemperatureMinTime
     */
    @Keep
    public Double getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    /**
     * @param apparentTemperatureMinTime The apparentTemperatureMinTime
     */
    @Keep
    public void setApparentTemperatureMinTime(Double apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
    }

    /**
     * @return The apparentTemperatureMax
     */
    @Keep
    public Double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    /**
     * @param apparentTemperatureMax The apparentTemperatureMax
     */
    @Keep
    public void setApparentTemperatureMax(Double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }

    /**
     * @return The apparentTemperatureMaxTime
     */
    @Keep
    public Double getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    /**
     * @param apparentTemperatureMaxTime The apparentTemperatureMaxTime
     */
    @Keep
    public void setApparentTemperatureMaxTime(Double apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
    }

    /**
     * @return The dewPoint
     */
    @Keep
    public Double getDewPoint() {
        return dewPoint;
    }

    /**
     * @param dewPoint The dewPoint
     */
    @Keep
    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    /**
     * @return The humidity
     */
    @Keep
    public Double getHumidity() {
        return humidity;
    }

    /**
     * @param humidity The humidity
     */
    @Keep
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    /**
     * @return The windSpeed
     */
    @Keep
    public Double getWindSpeed() {
        return windSpeed;
    }

    /**
     * @param windSpeed The windSpeed
     */
    @Keep
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    /**
     * @return The windBearing
     */
    @Keep
    public Double getWindBearing() {
        return windBearing;
    }

    /**
     * @param windBearing The windBearing
     */
    @Keep
    public void setWindBearing(Double windBearing) {
        this.windBearing = windBearing;
    }

    /**
     * @return The cloudCover
     */
    @Keep
    public Double getCloudCover() {
        return cloudCover;
    }

    /**
     * @param cloudCover The cloudCover
     */
    @Keep
    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
    }

    /**
     * @return The pressure
     */
    @Keep
    public Double getPressure() {
        return pressure;
    }

    /**
     * @param pressure The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * @return The ozone
     */
    @Keep
    public Double getOzone() {
        return ozone;
    }

    /**
     * @param ozone The ozone
     */
    @Keep
    public void setOzone(Double ozone) {
        this.ozone = ozone;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
