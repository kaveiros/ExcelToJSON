package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping actor catalog relationship
 */
public class ActorCatalogRelationship {

    @JsonProperty("catalog__id")
    private int catalogId;

    @JsonProperty("generic_catalog")
    private List<GenericCatalog> genericCatalog;

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public List<GenericCatalog> getGenericCatalog() {
        return genericCatalog;
    }

    public void setGenericCatalog(List<GenericCatalog> genericCatalog) {
        this.genericCatalog = genericCatalog;
    }
}
