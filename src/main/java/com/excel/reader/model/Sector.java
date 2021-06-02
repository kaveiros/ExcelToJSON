package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping sector section of JSON
 */
public class Sector {

    @JsonProperty("sector_description")
    private String sectorDescription;

    @JsonProperty("sector_name")
    private String sectorName;

    @JsonProperty("sector_type")
    private List<SectorType> sectorTypeList;

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public List<SectorType> getSectorTypeList() {
        return sectorTypeList;
    }

    public void setSectorTypeList(List<SectorType> sectorTypeList) {
        this.sectorTypeList = sectorTypeList;
    }

    public String getSectorDescription() {
        return sectorDescription;
    }

    public void setSectorDescription(String sectorDescription) {
        this.sectorDescription = sectorDescription;
    }
}
