package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CatalogAttribute {

    @JsonProperty("attribute__id")
    private int attributeId;

    @JsonProperty("catalog__id")
    private int getCatalogId;

    @JsonProperty("attribute_name")
    private String attributeName;

    @JsonProperty("attribute_value")
    private String attributeValue;

    @JsonProperty("attribute_description")
    private String attributeDescription;

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public int getGetCatalogId() {
        return getCatalogId;
    }

    public void setGetCatalogId(int getCatalogId) {
        this.getCatalogId = getCatalogId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
    }
}
