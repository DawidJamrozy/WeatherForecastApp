package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.os.Parcel;
import android.os.Parcelable;

import com.dawidj.weatherforecastapp.utils.RealmDataBinding;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dawidj on 24.10.2016.
 */

public class Currently extends RealmObject implements Observable, Parcelable, RealmDataBinding {

    @PrimaryKey
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
    @Ignore
    private transient PropertyChangeRegistry mCallbacks;

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks == null) {
            mCallbacks = new PropertyChangeRegistry();
        }
        mCallbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (mCallbacks != null) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public synchronized void notifyChange() {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, 0, null);
        }
    }

    public void notifyPropertyChanged(int fieldId) {
        if (mCallbacks != null) {
            mCallbacks.notifyCallbacks(this, fieldId, null);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Double windBearing) {
        this.windBearing = windBearing;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
        if (!isManaged()) {
            notifyPropertyChanged(com.dawidj.weatherforecastapp.BR._all);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
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
    }

    public Currently() {
    }

    protected Currently(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
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
    }

    public static final Creator<Currently> CREATOR = new Creator<Currently>() {
        @Override
        public Currently createFromParcel(Parcel source) {
            return new Currently(source);
        }

        @Override
        public Currently[] newArray(int size) {
            return new Currently[size];
        }
    };
}
