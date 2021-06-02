package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Class mapping actor collaboration relationship
 * section of JSON
 */
public class ActorCollaborationRelationship {

    @JsonProperty("collaboration__id")
    private long collaborationId;

    @JsonProperty("organizational_collaboration")
    private List<OrganizationalCollaboration> organizationalCollaborationList;


}
