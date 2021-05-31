package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping collaboration classification
 * section of JSON
 */
public class CollaborationClassification {

    @JsonProperty("collaboration_classification")
    private String collaborationClassification;

    public String getCollaborationClassification() {
        return collaborationClassification;
    }

    public void setCollaborationClassification(String collaborationClassification) {
        this.collaborationClassification = collaborationClassification;
    }
}
