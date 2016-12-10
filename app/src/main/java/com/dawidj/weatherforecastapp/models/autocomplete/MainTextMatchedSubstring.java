package com.dawidj.weatherforecastapp.models.autocomplete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class MainTextMatchedSubstring {
    @SerializedName("length")
    @Expose
    private Integer length;
    @SerializedName("offset")
    @Expose
    private Integer offset;

    /**
     *
     * @return
     * The length
     */
    public Integer getLength() {
        return length;
    }

    /**
     *
     * @param length
     * The length
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     *
     * @return
     * The offset
     */
    public Integer getOffset() {
        return offset;
    }

    /**
     *
     * @param offset
     * The offset
     */
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
