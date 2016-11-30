package com.dawidj.weatherforecastapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dawidj on 24.10.2016.
 */

public class Flags {

    @SerializedName("sources")
    @Expose
    private List<String> sources = new ArrayList<String>();
    @SerializedName("metno-license")
    @Expose
    private String metnoLicense;
    @SerializedName("isd-stations")
    @Expose
    private List<String> isdStations = new ArrayList<String>();
    @SerializedName("madis-stations")
    @Expose
    private List<String> madisStations = new ArrayList<String>();
    @SerializedName("units")
    @Expose
    private String units;

    /**
     *
     * @return
     * The sources
     */
    public List<String> getSources() {
        return sources;
    }

    /**
     *
     * @param sources
     * The sources
     */
    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    /**
     *
     * @return
     * The metnoLicense
     */
    public String getMetnoLicense() {
        return metnoLicense;
    }

    /**
     *
     * @param metnoLicense
     * The metno-license
     */
    public void setMetnoLicense(String metnoLicense) {
        this.metnoLicense = metnoLicense;
    }

    /**
     *
     * @return
     * The isdStations
     */
    public List<String> getIsdStations() {
        return isdStations;
    }

    /**
     *
     * @param isdStations
     * The isd-stations
     */
    public void setIsdStations(List<String> isdStations) {
        this.isdStations = isdStations;
    }

    /**
     *
     * @return
     * The madisStations
     */
    public List<String> getMadisStations() {
        return madisStations;
    }

    /**
     *
     * @param madisStations
     * The madis-stations
     */
    public void setMadisStations(List<String> madisStations) {
        this.madisStations = madisStations;
    }

    /**
     *
     * @return
     * The units
     */
    public String getUnits() {
        return units;
    }

    /**
     *
     * @param units
     * The units
     */
    public void setUnits(String units) {
        this.units = units;
    }
}
