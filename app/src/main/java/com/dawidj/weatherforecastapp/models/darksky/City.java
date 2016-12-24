package com.dawidj.weatherforecastapp.models.darksky;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.utils.RealmDataBinding;

import org.parceler.Parcel;

import io.realm.CityRealmProxy;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dawidj on 24.10.2016.
 */
@Parcel(implementations = {CityRealmProxy.class},
        value = org.parceler.Parcel.Serialization.BEAN,
        analyze = {City.class})
public class City extends RealmObject implements Observable, RealmDataBinding {

    @PrimaryKey
    private int id;
    private String name;
    private String placeId;
    private String adressDescription;
    private Integer sortPosition;
    private Double latitude;
    private Double longitude;
    private String timezone;
    private Currently currently;
    private Hourly hourly;
    private Daily daily;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    public Integer getSortPosition() {
        return sortPosition;
    }

    public void setSortPosition(Integer sortPosition) {
        this.sortPosition = sortPosition;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }
    @Bindable
    public String getAdressDescription() {
        return adressDescription;
    }

    public void setAdressDescription(String adressDescription) {
        this.adressDescription = adressDescription;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
        if (!isManaged()) {
            notifyPropertyChanged(BR._all);
        }
    }

    @Bindable
    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
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
