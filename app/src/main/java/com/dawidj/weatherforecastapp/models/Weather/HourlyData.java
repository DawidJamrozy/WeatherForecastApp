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
public class HourlyData extends BaseObservable {

    @Id
    private Long id;

    private String hourlyDataID;
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
    @SerializedName("precipType")
    @Expose
    private String precipType;


    @Generated(hash = 1739928541)
    public HourlyData(Long id, String hourlyDataID, Integer time, String summary,
            String icon, Double precipIntensity, Double precipProbability,
            Double temperature, Double apparentTemperature, Double dewPoint,
            Double humidity, Double windSpeed, Double windBearing,
            Double cloudCover, Double pressure, Double ozone, String precipType) {
        this.id = id;
        this.hourlyDataID = hourlyDataID;
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
        this.precipType = precipType;
    }

    @Generated(hash = 8296476)
    public HourlyData() {
    }


    @Keep
    public String getHourlyDataID() {
        return hourlyDataID;
    }

    @Keep
    public void setHourlyDataID(String hourlyDataID) {
        this.hourlyDataID = hourlyDataID;
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
        notifyPropertyChanged(BR._all);
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
     * @return The temperature
     */
    @Keep
    public Double getTemperature() {
        return temperature;
    }

    /**
     * @param temperature The temperature
     */
    @Keep
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
        notifyPropertyChanged(BR._all);
    }

    /**
     * @return The apparentTemperature
     */
    @Keep
    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    /**
     * @param apparentTemperature The apparentTemperature
     */
    @Keep
    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
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
    @Keep
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
