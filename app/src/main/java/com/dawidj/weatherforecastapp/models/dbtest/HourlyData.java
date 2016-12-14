package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Dawidj on 25.10.2016.
 */

@Entity
public class HourlyData extends BaseObservable implements Parcelable {

    @Id
    private Long id;

    //private Long hourlyID;

    @NotNull
    private String dataTag;

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

    @Generated(hash = 1013799781)
    public HourlyData(Long id, @NotNull String dataTag, Integer time, String summary, String icon,
            Double precipIntensity, Double precipProbability, Double temperature,
            Double apparentTemperature, Double dewPoint, Double humidity, Double windSpeed,
            Double windBearing, Double cloudCover, Double pressure, Double ozone, String precipType) {
        this.id = id;
        this.dataTag = dataTag;
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

    public String getDataTag() {
        return dataTag;
    }

    public void setDataTag(String dataTag) {
        this.dataTag = dataTag;
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

    public String getPrecipType() {
        return this.precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.dataTag);
        dest.writeValue(this.time);
        dest.writeString(this.summary);
        dest.writeString(this.icon);
        dest.writeValue(this.precipIntensity);
        dest.writeValue(this.precipProbability);
        dest.writeValue(this.temperature);
        dest.writeValue(this.apparentTemperature);
        dest.writeValue(this.dewPoint);
        dest.writeValue(this.humidity);
        dest.writeValue(this.windSpeed);
        dest.writeValue(this.windBearing);
        dest.writeValue(this.cloudCover);
        dest.writeValue(this.pressure);
        dest.writeValue(this.ozone);
        dest.writeString(this.precipType);
    }

    protected HourlyData(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.dataTag = in.readString();
        this.time = (Integer) in.readValue(Integer.class.getClassLoader());
        this.summary = in.readString();
        this.icon = in.readString();
        this.precipIntensity = (Double) in.readValue(Double.class.getClassLoader());
        this.precipProbability = (Double) in.readValue(Double.class.getClassLoader());
        this.temperature = (Double) in.readValue(Double.class.getClassLoader());
        this.apparentTemperature = (Double) in.readValue(Double.class.getClassLoader());
        this.dewPoint = (Double) in.readValue(Double.class.getClassLoader());
        this.humidity = (Double) in.readValue(Double.class.getClassLoader());
        this.windSpeed = (Double) in.readValue(Double.class.getClassLoader());
        this.windBearing = (Double) in.readValue(Double.class.getClassLoader());
        this.cloudCover = (Double) in.readValue(Double.class.getClassLoader());
        this.pressure = (Double) in.readValue(Double.class.getClassLoader());
        this.ozone = (Double) in.readValue(Double.class.getClassLoader());
        this.precipType = in.readString();
    }

    public static final Creator<HourlyData> CREATOR = new Creator<HourlyData>() {
        @Override
        public HourlyData createFromParcel(Parcel source) {
            return new HourlyData(source);
        }

        @Override
        public HourlyData[] newArray(int size) {
            return new HourlyData[size];
        }
    };
}
