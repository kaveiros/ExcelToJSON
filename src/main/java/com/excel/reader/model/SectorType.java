package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping sector type  part
 * of json
 */
public class SectorType {

    @JsonProperty("sector_type_label")
    private String sectorTypeLabel;

    @JsonProperty("sector_type_notation")
    private String sectorTypeNotation;

    @JsonProperty("sector_type_parent__id")
    private int sectorTypeParentId;

    public String getSectorTypeLabel() {
        return sectorTypeLabel;
    }

    public void setSectorTypeLabel(String sectorTypeLabel) {
        this.sectorTypeLabel = sectorTypeLabel;
    }

    public String getSectorTypeNotation() {
        return sectorTypeNotation;
    }

    public void setSectorTypeNotation(String sectorTypeNotation) {
        this.sectorTypeNotation = sectorTypeNotation;
    }

    public int getSectorTypeParentId() {
        return sectorTypeParentId;
    }

    public void setSectorTypeParentId(int sectorTypeParentId) {
        this.sectorTypeParentId = sectorTypeParentId;
    }
}
