package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping technology readiness section JSON
 */
public class TechnologyReadiness {

    @JsonProperty("technology_readiness_level__id")
    private long technologyReadinessLevelId;

    @JsonProperty("technology_readiness_level")
    private String technologyReadinessLevel;

    @JsonProperty("technology_readiness_level_description")
    private String technologyReadinessLevelDescription;

    public long getTechnologyReadinessLevelId() {
        return technologyReadinessLevelId;
    }

    public void setTechnologyReadinessLevelId(long technologyReadinessLevelId) {
        this.technologyReadinessLevelId = technologyReadinessLevelId;
    }

    public String getTechnologyReadinessLevel() {
        return technologyReadinessLevel;
    }

    public void setTechnologyReadinessLevel(String technologyReadinessLevel) {
        this.technologyReadinessLevel = technologyReadinessLevel;
    }

    public String getTechnologyReadinessLevelDescription() {
        return technologyReadinessLevelDescription;
    }

    public void setTechnologyReadinessLevelDescription(String technologyReadinessLevelDescription) {
        this.technologyReadinessLevelDescription = technologyReadinessLevelDescription;
    }
}
