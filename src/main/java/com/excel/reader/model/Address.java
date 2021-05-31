package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping address section of JSON
 */
public class Address {

    @JsonProperty("address_id")
    private int addressId;

    @JsonProperty("location_id")
    private int locationId;

    @JsonProperty("full_address")
    private String address;

    @JsonProperty("admin_unit_l1")
    private String adminUnitIdL1;

    @JsonProperty("admin_unit_l2")
    private String adminUnitIdL2;

    @JsonProperty("state")
    private String state;

    @JsonProperty("postal_code")
    private String postalCode;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdminUnitIdL1() {
        return adminUnitIdL1;
    }

    public void setAdminUnitIdL1(String adminUnitIdL1) {
        this.adminUnitIdL1 = adminUnitIdL1;
    }

    public String getAdminUnitIdL2() {
        return adminUnitIdL2;
    }

    public void setAdminUnitIdL2(String adminUnitIdL2) {
        this.adminUnitIdL2 = adminUnitIdL2;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
