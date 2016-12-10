package com.dawidj.weatherforecastapp.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class OpeningHours {
    @SerializedName("open_now")
    @Expose
    private Boolean openNow;
    @SerializedName("periods")
    @Expose
    private List<Period> periods = null;
    @SerializedName("weekday_text")
    @Expose
    private List<String> weekdayText = null;

    /**
     *
     * @return
     * The openNow
     */
    public Boolean getOpenNow() {
        return openNow;
    }

    /**
     *
     * @param openNow
     * The open_now
     */
    public void setOpenNow(Boolean openNow) {
        this.openNow = openNow;
    }

    /**
     *
     * @return
     * The periods
     */
    public List<Period> getPeriods() {
        return periods;
    }

    /**
     *
     * @param periods
     * The periods
     */
    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    /**
     *
     * @return
     * The weekdayText
     */
    public List<String> getWeekdayText() {
        return weekdayText;
    }

    /**
     *
     * @param weekdayText
     * The weekday_text
     */
    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }
}
