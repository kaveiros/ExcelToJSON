package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping technology section
 */
public class Technology {

    @JsonProperty("technology_name")
    private String technologyName;

    @JsonProperty("technology_description")
    private String getTechnologyDescription;

    @JsonProperty("technology_type")
    private List<TechnologyType> technologyType;

    public String getTechnologyName() {
        return technologyName;
    }

    public void setTechnologyName(String technologyName) {
        this.technologyName = technologyName;
    }

    public String getGetTechnologyDescription() {
        return getTechnologyDescription;
    }

    public void setGetTechnologyDescription(String getTechnologyDescription) {
        this.getTechnologyDescription = getTechnologyDescription;
    }

    public List<TechnologyType> getTechnologyType() {
        return technologyType;
    }

    public void setTechnologyType(List<TechnologyType> technologyType) {
        this.technologyType = technologyType;
    }
}
