package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping organizational collaboration
 * section of JSON
 */
public class OrganizationalCollaboration {

    @JsonProperty("collaboration_identifier")
    private List<CollaborationIdentifier> collaborationIdentifierList;

    @JsonProperty("collaboration_classification")
    private List<CollaborationClassification> collaborationClassificationList;

    public List<CollaborationIdentifier> getCollaborationIdentifierList() {
        return collaborationIdentifierList;
    }

    public void setCollaborationIdentifierList(List<CollaborationIdentifier> collaborationIdentifierList) {
        this.collaborationIdentifierList = collaborationIdentifierList;
    }

    public List<CollaborationClassification> getCollaborationClassificationList() {
        return collaborationClassificationList;
    }

    public void setCollaborationClassificationList(List<CollaborationClassification> collaborationClassificationList) {
        this.collaborationClassificationList = collaborationClassificationList;
    }
}
