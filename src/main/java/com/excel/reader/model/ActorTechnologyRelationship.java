package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping actor technology relationship
 * section of JSON
 */
public class ActorTechnologyRelationship {


    @JsonProperty("actor_technology_relationship")
    private List<Technology> technologyList;

    public List<Technology> getTechnologyList() {
        return technologyList;
    }

    public void setTechnologyList(List<Technology> technologyList) {
        this.technologyList = technologyList;
    }
}
