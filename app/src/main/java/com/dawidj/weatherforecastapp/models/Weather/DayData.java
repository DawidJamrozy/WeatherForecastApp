package com.dawidj.weatherforecastapp.models.Weather;

import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.dawidj.weatherforecastapp.BR;
import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.utils.Const;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dawidj on 03.12.2016.
 */

public class DayData extends BaseObservable {

    private int tempMin;
    private int tempMax;
    private String icon;
    private int time;
    private SimpleDateFormat simpleDayDateFormat = new SimpleDateFormat(Const.DAY_FORMAT);

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
        notifyPropertyChanged(BR._all);
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
        notifyPropertyChanged(BR._all);
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
        notifyPropertyChanged(BR._all);
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        notifyPropertyChanged(BR._all);
    }

    public DayData() {
    }

    public String getTemp() {
        return getTempMax() + Const.CELSIUS_DEGREE + " / " + getTempMin() + Const.CELSIUS_DEGREE;
    }

    public String getSummary() {
        String summary = null;
        switch (getIcon()) {
            case "rain":
                summary = "Deszcz";
                break;
            case "fog":
                summary = "Mgła";
                break;
            case "partly-cloudy-day":
                summary = "Częściowe zachmurzenie w ciągu dnia";
                break;
            case "partly-cloudy-night":
                summary = "Częściowe zachmurzenie w ciągu nocy";
                break;
            case "snow":
                summary = "Śnieg";
                break;
            case "sleet":
                summary = "Śnieg z deszczem";
                break;
            case "clear-day":
                summary = "Pogodnie";
                break;
            case "clear-night":
                summary = "Pogodnie";
                break;
            case "wind":
                summary = "Wiatr";
                break;
            case "cloudy":
                summary = "Pochmurnie";
                break;
            default:
                break;
        }
        return summary;
    }

    public int getImageSrc() {
        int iconCode = 0;
        switch (getIcon()) {
            case "rain":
                iconCode = R.drawable.rain;
                break;
            case "fog":
                iconCode = R.drawable.fog;
                break;
            case "partly-cloudy-day":
                iconCode = R.drawable.partly_cloudy_day;
                break;
            case "partly-cloudy-night":
                iconCode = R.drawable.partly_cloudy_night;
                break;
            case "snow":
                iconCode = R.drawable.snow;
                break;
            case "sleet":
                iconCode = R.drawable.sleet;
                break;
            case "clear-day":
                iconCode = R.drawable.clear_day;
                break;
            case "clear-night":
                iconCode = R.drawable.clear_night;
                break;
            case "wind":
                iconCode = R.drawable.wind;
                break;
            case "cloudy":
                iconCode = R.drawable.cloudy;
                break;
            default:
                break;
        }
        return iconCode;

    }

    @BindingAdapter("imageSrc")
    public static void loadImage(ImageView imageView, int imageSrc) {
        Picasso.with(imageView.getContext()).load(imageSrc).into(imageView);
    }

    public String getDayName() {
        Date date = new Date(getTime() * 1000L);
        String dayName = simpleDayDateFormat.format(date);
        return dayName.substring(0, 1).toUpperCase() + dayName.substring(1);
    }
}
