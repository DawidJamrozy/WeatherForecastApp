package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.utils.RealmDataBinding;

import org.parceler.Parcel;

import io.realm.CurrentlyRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dawidj on 24.10.2016.
 */
@Parcel(implementations = { CurrentlyRealmProxy.class },
        value = org.parceler.Parcel.Serialization.BEAN,
        analyze = { Currently.class })
public class Currently extends RealmObject implements Observable, RealmDataBinding {

    @PrimaryKey
    private int id;

    private Integer time;

    private String summary;

    private String icon;

    private Double precipIntensity;

    private Double precipProbability;

    private Double temperature;

    private Double apparentTemperature;

    private Double dewPoint;

    private Double humidity;


    private Double windSpeed;

    private Double windBearing;

    private Double cloudCover;

    private Double pressure;

    private Double ozone;

    public Currently() {
    }

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
    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(Double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Double windBearing) {
        this.windBearing = windBearing;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
}
