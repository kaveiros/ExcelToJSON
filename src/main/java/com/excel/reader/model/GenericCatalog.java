package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping generic catalog section
 *
 */
public class GenericCatalog {

    @JsonProperty( "catalog_name")
    private String catalogName;

    @JsonProperty("catalog_description")
    private String catalogDescription;

    @JsonProperty("catalog_value")
    private String catalogValue;

    @JsonProperty("catalog_attribute")
    private List<CatalogAttribute> catalogAttribute;

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogDescription() {
        return catalogDescription;
    }

    public void setCatalogDescription(String catalogDescription) {
        this.catalogDescription = catalogDescription;
    }

    public String getCatalogValue() {
        return catalogValue;
    }

    public void setCatalogValue(String catalogValue) {
        this.catalogValue = catalogValue;
    }

    public List<CatalogAttribute> getCatalogAttribute() {
        return catalogAttribute;
    }

    public void setCatalogAttribute(List<CatalogAttribute> catalogAttribute) {
        this.catalogAttribute = catalogAttribute;
    }
}
