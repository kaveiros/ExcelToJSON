package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *Class mapping collaboration
 */
public class CollaborationIdentifier {

    @JsonProperty("collaboration_identifier")
    private String collaborationIdentifier;

    public String getCollaborationIdentifier() {
        return collaborationIdentifier;
    }

    public void setCollaborationIdentifier(String collaborationIdentifier) {
        this.collaborationIdentifier = collaborationIdentifier;
    }
}
