package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping actor technology relationship
 * section of JSON
 */
public class ActorTechnologyRelationship {


    @JsonProperty("technology")
    private List<Technology> technologyList;

    public List<Technology> getTechnologyList() {
        return technologyList;
    }

    public void setTechnologyList(List<Technology> technologyList) {
        this.technologyList = technologyList;
    }
}
