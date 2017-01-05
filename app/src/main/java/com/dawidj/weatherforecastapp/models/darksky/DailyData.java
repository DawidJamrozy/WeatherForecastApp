package com.dawidj.weatherforecastapp.models.darksky;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.utils.RealmDataBinding;

import org.parceler.Parcel;

import io.realm.DailyDataRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dawidj on 25.10.2016.
 */
@Parcel(implementations = {DailyDataRealmProxy.class},
        value = org.parceler.Parcel.Serialization.BEAN,
        analyze = {DailyData.class})
public class DailyData extends RealmObject implements Observable, RealmDataBinding {

    @PrimaryKey
    private int id;
    private Integer time;
    private String name;
    private String summary;
    private String placeId;
    private String icon;
    @Ignore
    private Double sunriseTime;
    @Ignore
    private Double sunsetTime;
    @Ignore
    private Double moonPhase;
    @Ignore
    private Double precipIntensity;
    @Ignore
    private Double precipIntensityMax;
    @Ignore
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
    @Ignore
    private Double dewPoint;
    private Double humidity;
    private Double windSpeed;
    @Ignore
    private Double windBearing;
    @Ignore
    private Double cloudCover;
    private Double pressure;
    @Ignore
    private Double ozone;

    public DailyData() {
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
    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Double getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(Double sunriseTime) {
        this.sunriseTime = sunriseTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(Double sunsetTime) {
        this.sunsetTime = sunsetTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(Double moonPhase) {
        this.moonPhase = moonPhase;
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
    public Double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public void setPrecipIntensityMax(Double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public void setPrecipIntensityMaxTime(Double precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
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
    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public void setTemperatureMinTime(Double temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public void setTemperatureMaxTime(Double temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(Double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public void setApparentTemperatureMinTime(Double apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(Double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public Double getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    public void setApparentTemperatureMaxTime(Double apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
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
