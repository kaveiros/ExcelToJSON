package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 */
public class ActorTRLRelationship {

    @JsonProperty("technology_readiness_level__id")
    private long technologyReadinessLevelId;

    @JsonProperty("technology_readiness_level")
    private List<TechnologyReadiness> technologyReadinessList;

    public long getTechnologyReadinessLevelId() {
        return technologyReadinessLevelId;
    }

    public void setTechnologyReadinessLevelId(long technologyReadinessLevelId) {
        this.technologyReadinessLevelId = technologyReadinessLevelId;
    }

    public List<TechnologyReadiness> getTechnologyReadinessList() {
        return technologyReadinessList;
    }

    public void setTechnologyReadinessList(List<TechnologyReadiness> technologyReadinessList) {
        this.technologyReadinessList = technologyReadinessList;
    }
}
