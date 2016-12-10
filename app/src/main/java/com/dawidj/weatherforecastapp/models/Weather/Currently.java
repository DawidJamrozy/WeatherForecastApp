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
        notifyPropertyChanged(BR._all);

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
        notifyPropertyChanged(BR._all);

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
        notifyPropertyChanged(BR._all);

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
        notifyPropertyChanged(BR._all);

    }

    /**
     * @return The humidity
     */
    @Keep
    public Double getHumidity() {
        return humidity * 100;
    }

    /**
     * @param humidity The humidity
     */
    @Keep
    public void setHumidity(Double humidity) {
        this.humidity = humidity;
        notifyPropertyChanged(BR._all);

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
        notifyPropertyChanged(BR._all);

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
        notifyPropertyChanged(BR._all);

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
        notifyPropertyChanged(BR._all);

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
        notifyPropertyChanged(BR._all);
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
        notifyPropertyChanged(BR._all);

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
