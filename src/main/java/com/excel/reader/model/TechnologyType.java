package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping technology type
 * section of JSON.
 */
public class TechnologyType {

    @JsonProperty("technology_type_label")
    private String technologyTypeLabel;

    @JsonProperty("technology_type_notation")
    private String getTechnologyTypeNotation;

    public String getTechnologyTypeLabel() {
        return technologyTypeLabel;
    }

    public void setTechnologyTypeLabel(String technologyTypeLabel) {
        this.technologyTypeLabel = technologyTypeLabel;
    }

    public String getGetTechnologyTypeNotation() {
        return getTechnologyTypeNotation;
    }

    public void setGetTechnologyTypeNotation(String getTechnologyTypeNotation) {
        this.getTechnologyTypeNotation = getTechnologyTypeNotation;
    }
}
