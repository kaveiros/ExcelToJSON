package com.excel.reader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class mapping actor_type section of JSON
 */
public class ActorType {

    @JsonProperty("actor_type_id")
    private int actorTypeId;

    @JsonProperty("actor_type_label")
    private String actorTypeLabel;

    @JsonProperty("actor_type_notation")
    private String actorTypeNotation;

    @JsonProperty("actor_type_definition")
    private String actorTypeDefinition;

    public int getActorTypeId() {
        return actorTypeId;
    }

    public void setActorTypeId(int actorTypeId) {
        this.actorTypeId = actorTypeId;
    }

    public String getActorTypeLabel() {
        return actorTypeLabel;
    }

    public void setActorTypeLabel(String actorTypeLabel) {
        this.actorTypeLabel = actorTypeLabel;
    }

    public String getActorTypeNotation() {
        return actorTypeNotation;
    }

    public void setActorTypeNotation(String actorTypeNotation) {
        this.actorTypeNotation = actorTypeNotation;
    }

    public String getActorTypeDefinition() {
        return actorTypeDefinition;
    }

    public void setActorTypeDefinition(String actorTypeDefinition) {
        this.actorTypeDefinition = actorTypeDefinition;
    }
}
