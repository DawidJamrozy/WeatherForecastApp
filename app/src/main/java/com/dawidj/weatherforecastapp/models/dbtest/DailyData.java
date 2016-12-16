package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.utils.RealmDataBinding;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dawidj on 25.10.2016.
 */
@org.parceler.Parcel(implementations = {DailyData.class},
        value = org.parceler.Parcel.Serialization.BEAN,
        analyze = {DailyData.class})
public class DailyData extends RealmObject implements Observable, RealmDataBinding {

    @PrimaryKey
    private int id;

    private Integer time;

    private String summary;

    private String icon;

    private Double sunriseTime;

    private Double sunsetTime;

    private Double moonPhase;

    private Double precipIntensity;

    private Double precipIntensityMax;

    private Double precipIntensityMaxTime;

    private Double precipProbability;

    private String precipType;

    private Double temperatureMin;

    private Double temperatureMinTime;

    private Double temperatureMax;

    private Double temperatureMaxTime;

    private Double apparentTemperatureMin;

    private Double apparentTemperatureMinTime;

    private Double apparentTemperatureMax;

    private Double apparentTemperatureMaxTime;

    private Double dewPoint;

    private Double humidity;

    private Double windSpeed;

    private Double windBearing;

    private Double cloudCover;

    private Double pressure;

    private Double ozone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(Double sunriseTime) {
        this.sunriseTime = sunriseTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(Double sunsetTime) {
        this.sunsetTime = sunsetTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(Double precipIntensity) {
        this.precipIntensity = precipIntensity;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public void setPrecipIntensityMax(Double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public void setPrecipIntensityMaxTime(Double precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(Double precipProbability) {
        this.precipProbability = precipProbability;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public void setTemperatureMinTime(Double temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public void setTemperatureMaxTime(Double temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(Double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public void setApparentTemperatureMinTime(Double apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(Double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    public void setApparentTemperatureMaxTime(Double apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(Double windBearing) {
        this.windBearing = windBearing;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Double cloudCover) {
        this.cloudCover = cloudCover;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    public Double getOzone() {
        return ozone;
    }

    public void setOzone(Double ozone) {
        this.ozone = ozone;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
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
}
