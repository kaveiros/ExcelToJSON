package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Location {


    @JsonProperty("geographic_name")
    private String geographicName;

    @JsonProperty("geographic_identifier")
    private String geographicIdentifier;

    @JsonProperty("geometry")
    private Geometry geometry;

    @JsonProperty("address")
    private List<Address> addressList;

    public String getGeographicName() {
        return geographicName;
    }

    public void setGeographicName(String geographicName) {
        this.geographicName = geographicName;
    }

    public String getGeographicIdentifier() {
        return geographicIdentifier;
    }

    public void setGeographicIdentifier(String geographicIdentifier) {
        this.geographicIdentifier = geographicIdentifier;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }
}
