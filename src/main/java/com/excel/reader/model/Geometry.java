package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping geometry section of the JSON
 */
public class Geometry {

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("lon")
    private String lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
