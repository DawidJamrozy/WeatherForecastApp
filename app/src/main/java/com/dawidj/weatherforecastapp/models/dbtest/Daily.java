package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.utils.DailyDataParcelConverter;
import com.dawidj.weatherforecastapp.utils.RealmDataBinding;

import org.parceler.ParcelPropertyConverter;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dawidj on 24.10.2016.
 */
@org.parceler.Parcel(implementations = {Daily.class},
        value = org.parceler.Parcel.Serialization.BEAN,
        analyze = {Daily.class})
public class Daily extends RealmObject implements Observable, RealmDataBinding {

    @PrimaryKey
    private int id;

    private String summary;

    private String icon;

    private RealmList<DailyData> data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public RealmList<DailyData> getData() {
        return data;
    }

    @ParcelPropertyConverter(DailyDataParcelConverter.class)
    public void setData(RealmList<DailyData> data) {
        this.data = data;
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
