package com.dawidj.weatherforecastapp.models.autocomplete;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dawidj on 10.12.2016.
 */

public class StructuredFormatting {
    @SerializedName("main_text")
    @Expose
    private String mainText;
    @SerializedName("main_text_matched_substrings")
    @Expose
    private List<MainTextMatchedSubstring> mainTextMatchedSubstrings = null;
    @SerializedName("secondary_text")
    @Expose
    private String secondaryText;

    /**
     *
     * @return
     * The mainText
     */
    public String getMainText() {
        return mainText;
    }

    /**
     *
     * @param mainText
     * The main_text
     */
    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    /**
     *
     * @return
     * The mainTextMatchedSubstrings
     */
    public List<MainTextMatchedSubstring> getMainTextMatchedSubstrings() {
        return mainTextMatchedSubstrings;
    }

    /**
     *
     * @param mainTextMatchedSubstrings
     * The main_text_matched_substrings
     */
    public void setMainTextMatchedSubstrings(List<MainTextMatchedSubstring> mainTextMatchedSubstrings) {
        this.mainTextMatchedSubstrings = mainTextMatchedSubstrings;
    }

    /**
     *
     * @return
     * The secondaryText
     */
    public String getSecondaryText() {
        return secondaryText;
    }

    /**
     *
     * @param secondaryText
     * The secondary_text
     */
    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }
}
