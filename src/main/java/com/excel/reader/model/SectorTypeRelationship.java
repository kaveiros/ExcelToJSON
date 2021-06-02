package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class used to map sector type relationship
 * part of JSON
 */
public class SectorTypeRelationship {

    @JsonProperty( "sector__id")
    private long sectorId;

    @JsonProperty("sector")
    private List<Sector> sectorList;

    public long getSectorId() {
        return sectorId;
    }

    public void setSectorId(long sectorId) {
        this.sectorId = sectorId;
    }

    public List<Sector> getSectorList() {
        return sectorList;
    }

    public void setSectorList(List<Sector> sectorList) {
        this.sectorList = sectorList;
    }
}
