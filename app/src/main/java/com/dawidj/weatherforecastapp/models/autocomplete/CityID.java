package com.dawidj.weatherforecastapp.models.autocomplete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class CityID {

    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions = null;
    @SerializedName("status")
    @Expose
    private String status;

    /**
     *
     * @return
     * The predictions
     */
    public List<Prediction> getPredictions() {
        return predictions;
    }

    /**
     *
     * @param predictions
     * The predictions
     */
    public void setPredictions(List<Prediction> predictions) {
        this.predictions = predictions;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
