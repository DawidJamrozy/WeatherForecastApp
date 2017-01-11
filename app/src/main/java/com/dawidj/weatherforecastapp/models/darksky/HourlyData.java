package com.dawidj.weatherforecastapp.models.darksky;

import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.widget.ImageView;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.utils.RealmDataBinding;
import com.squareup.picasso.Picasso;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dawidj on 25.10.2016.
 */

public class HourlyData extends RealmObject implements Observable, RealmDataBinding {

    @PrimaryKey
    private int id;
    private String placeId;
    private Integer time;
    private String summary;
    private String icon;
    @Ignore
    private Double precipIntensity;
    @Ignore
    private Double precipProbability;
    private Double temperature;
    private Double apparentTemperature;
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
    private String precipType;

    public HourlyData() {
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

    public int getImageSrc() {
        int iconCode = 0;

        if (getPrecipType() == null) {
            iconCode = R.drawable.drop;
        } else {
            switch (getPrecipType()) {
                case "rain":
                    iconCode = R.drawable.drop;
                    break;
                case "snow":
                    iconCode = R.drawable.snowflakes;
                    break;
                case "sleet":
                    iconCode = R.drawable.sleet_icon;
                    break;
            }
        }
        return iconCode;

    }

    @BindingAdapter({"imageSrc"})
    public static void loadImage(ImageView imageView, int imageSrc) {
        Picasso.with(imageView.getContext()).load(imageSrc).into(imageView);
    }
}
